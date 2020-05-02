(defproject org.pinkgorilla/gorilla-ui "0.1.31-SNAPSHOT"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]

  :release-tasks [["vcs" "assert-committed"]
                  ["bump-version" "release"]
                  ["vcs" "commit" "Release %s"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["bump-version"]
                  ["vcs" "commit" "Begin %s"]
                  ["vcs" "push"]]

  :source-paths ["src"] ; "test"
  ;:test-paths ["test"]

  :resource-paths  ["resources"
                    "node_modules/leaflet/dist"
                    "node_modules/mathjax/es5"
                    "node_modules/ag-grid-community"]

  :plugins [[lein-shell "0.5.0"]]

  :dependencies [;; OUR BUILD NEEDS TO BE UPDATED. WE HAVE DUPLICATE DEPENDENCIES
                 ;; DO NOT FORGET TO CHECK SHADOW-CLJS.EDN - IT CONTAINS SIMILAR DEPENDENCIES
                 [reagent "0.10.0"
                  :exclusions [org.clojure/tools.reader
                               cljsjs/react
                               cljsjs/react-dom]]
                 [thi.ng/strf "0.2.2"]
                ; [thheller/shadow-cljsjs "0.0.21"]
                 ;[com.taoensso/timbre "4.10.0"] ; clojurescript logging awb99: this fucks up kernel-cljs-shadowdeps
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"] ;; awb99: in encoding, and clj/cljs proof

                 [org.pinkgorilla/gorilla-renderable-ui "0.1.33"]]

  :profiles {:test {:source-paths ["src" "test"]
                    :test-paths ["test"]}

             :demo {:source-paths ["profiles/demo/src"]
                    :dependencies [; shadow-cljs MAY NOT be a dependency in lein deps :tree -> if so, bundeler will fail because shadow contains core.async which is not compatible with self hosted clojurescript
                                   ;[thheller/shadow-cljs "2.8.80"]
                                   ;[thheller/shadow-cljsjs "0.0.21"]
                                   ]}

             :dev {:dependencies [;[thheller/shadow-cljs "2.8.80"]
                                  ;; [thheller/shadow-cljsjs "0.0.21"]
                                  [clj-kondo "2019.11.23"]]
                   :plugins      [[lein-cljfmt "0.6.6"]
                                  ;; [lein-cloverage "1.1.2"]
                                  ]
                   :aliases      {"clj-kondo" ["run" "-m" "clj-kondo.main"]}
                   :cloverage    {:codecov? true
                                  ;; In case we want to exclude stuff
                                  ;; :ns-exclude-regex [#".*util.instrument"]
                                  ;; :test-ns-regex [#"^((?!debug-integration-test).)*$$"]
                                  }
                   ;; TODO : Make cljfmt really nice : https://devhub.io/repos/bbatsov-cljfmt
                   :cljfmt       {:indents {as->                [[:inner 0]]
                                            with-debug-bindings [[:inner 0]]
                                            merge-meta          [[:inner 0]]
                                            try-if-let          [[:block 1]]}}}}

  :aliases {"clean"  ^{:doc "Cleans build artefacts."}
            ["shell" "./scripts/clean.sh"]
            "bump-version" ^{:doc "Increases project.clj version number (used by CI)."}
            ["change" "version" "leiningen.release/bump-version"]
            ;"build-shadow-ci" ["run" "-m" "shadow.cljs.devtools.cli" "compile" ":demo"] ; :ci
            ;"shadow-watch-demo" ["run" "-m" "shadow.cljs.devtools.cli" "watch" ":demo"]
            "build-test"  ^{:doc "Builds Bundle. Gets executed automatically before unit tests."}
            ["with-profile" "+test" "shell" "shadow-cljs" "compile" "ci"]

            "test-run" ^{:doc "Runs unit tests. Does not build the bundle first.."}
            ["shell" "./node_modules/karma/bin/karma" "start" "--single-run"]

           ; "test-js"                          ^{:doc "Test compiled JavaScript."}
           ; ["shell" "npm" "run" "test"]
            "test-js" ^{:doc "Run Unit Tests. Will compile bundle first."}
            ["do" "build-test" ["test-run"]]

            "demo"  ^{:doc "Runs UI components via webserver."}
            ["shell" "shadow-cljs" "watch" "demo"]

            "embed"  ^{:doc "Runs UI embedding via webserver."}
            ["shell" "shadow-cljs" "watch" "embed"]})
