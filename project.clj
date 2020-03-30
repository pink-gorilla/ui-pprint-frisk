(defproject org.pinkgorilla/gorilla-ui "0.1.21-SNAPSHOT"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]
  :source-paths ["src" "test"]
  :test-paths ["test"]
  :resource-paths  ["resources"] 
  
  :plugins [[lein-shell "0.5.0"]]

  :dependencies [[reagent "0.8.1"]
                 [thi.ng/strf "0.2.2"]
                 [thheller/shadow-cljsjs "0.0.21"]
                 ;[com.taoensso/timbre "4.10.0"] ; clojurescript logging awb99: this fucks up kernel-cljs-shadowdeps
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"] ;; awb99: in encoding, and clj/cljs proof
                 
                 ;; OUR BUILD NEEDS TO BE UPDATED. WE HAVE DUPLICATE DEPENDENCIES
                 ;; DO NOT FORGET TO CHECK SHADOW-CLJS.EDN - IT CONTAINS SIMILAR DEPENDENCIES
                 [org.pinkgorilla/gorilla-renderable-ui "0.1.22" ]] 

  :profiles {:builder {:dependencies [[thheller/shadow-cljs "2.8.80"]] } ; shadow-cljs MAY NOT be a dependency in lein deps :tree -> if so, bundelr will fail because shadow contains core.async which is not compatible with self hosted clojurescript
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

            ;"build-shadow-ci" ["run" "-m" "shadow.cljs.devtools.cli" "compile" ":demo"] ; :ci
            ;"shadow-watch-demo" ["run" "-m" "shadow.cljs.devtools.cli" "watch" ":demo"]
            "build-test"  ^{:doc "Builds Bundle. Gets executed automatically before unit tests."}
            ["shell" "shadow-cljs" "compile" "ci"]

            "demo"  ^{:doc "Runs UI components via webserver."}
            ["shell" "shadow-cljs" "watch" "demo"]

            "test-run" ^{:doc "Runs unit tests. Does not build the bundle first.."}
            ["shell" "./node_modules/karma/bin/karma" "start" "--single-run"]

           ; "test-js"                          ^{:doc "Test compiled JavaScript."}
           ; ["shell" "npm" "run" "test"]
            "test-js" ^{:doc "Run Unit Tests. Will compile bundle first."}
            ["do" "build-test" ["test-run"]]

            "bump-version" ^{:doc "Increases project.clj version number (used by CI)."}
            ["change" "version" "leiningen.release/bump-version"]}

  :release-tasks [["vcs" "assert-committed"]
                  ["bump-version" "release"]
                  ["vcs" "commit" "Release %s"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["bump-version"]
                  ["vcs" "commit" "Begin %s"]
                  ["vcs" "push"]])
