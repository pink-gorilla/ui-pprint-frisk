(defproject org.pinkgorilla/gorilla-ui "0.3.8"
  :description "Reagent components for data visualisation."
  :url "https://github.com/pink-gorilla/gorilla-ui"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]

  :min-lein-version "2.9.4" ; nrepl 0.7.0

  :prep-tasks ["css"]

  :release-tasks [["vcs" "assert-committed"]
                  ["bump-version" "release"]
                  ["vcs" "commit" "Release %s"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["bump-version"]
                  ["vcs" "commit" "Begin %s"]
                  ["vcs" "push"]]

  :source-paths ["src"] ; "test"
  :test-paths ["test"]
  :target-path  "target/jar"
  :resource-paths  ["resources" ; not from npm
                    "target/webly" ; bundle
                    "target/node_modules"] ; css png resources from npm modules
  :clean-targets ^{:protect false} [:target-path
                                    [:demo :builds :app :compiler :output-dir]
                                    [:demo :builds :app :compiler :output-to]]


  :dependencies [; gorilla-ui is a cljs project, so in here are cljs dependencies
                 ;[org.clojure/clojurescript "1.10.773"]
                 [thi.ng/strf "0.2.2"
                  :exclusions [org.clojure/clojurescript]]
                 ;[com.taoensso/timbre "4.10.0"] ; clojurescript logging awb99: this fucks up kernel-cljs-shadowdeps
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"] ;; awb99: in encoding, and clj/cljs proof
                 [fipp "0.6.23"] ; edn pretty printing - for examples (examples get shipped)
                 [org.pinkgorilla/pinkie "0.3.3"]]

  :profiles {:test {:source-paths ["src"
                                   "test"]
                    :test-paths   ["test"]}

             :embed {:source-paths ["profiles/embed/src"
                                    "test"]}

             :demo {:dependencies []
                    :resource-paths  ["profiles/demo/resources"]
                    :source-paths ["src"
                                   "profiles/demo/src"
                                   "test"]}
             :dev  {:dependencies [[org.clojure/clojure "1.10.3"]
                                   ; shadow-cljs MAY NOT be a dependency in lein deps :tree -> if so, bundeler will fail because shadow contains core.async which is not compatible with self hosted clojurescript
                                   [org.pinkgorilla/webly "0.2.10"] ; brings shadow-cljs
                                   [clj-kondo "2021.03.31"]]
                    :plugins      [[lein-cljfmt "0.6.6"]
                                   [lein-cloverage "1.1.2"]
                                   [lein-shell "0.5.0"]
                                   [lein-ancient "0.6.15"]]
                    :aliases      {"clj-kondo"
                                   ["run" "-m" "clj-kondo.main"]
                                   "bump-version" ^{:doc "Increases project.clj version number (used by CI)."}
                                   ["change" "version" "leiningen.release/bump-version"]}
                    :cloverage    {:codecov? true ; https://github.com/codecov/example-clojure
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
            "css"  ^{:doc "Copies certain npm package dependecies"}
            ["shell" "./scripts/copy_res.sh"]

            ;; TEST

            "build-test"  ^{:doc "Builds Bundle. Gets executed automatically before unit tests."}
            ["with-profile" "+test" "run" "-m" "shadow.cljs.devtools.cli" "compile" "ci"]

            "test-run" ^{:doc "Runs unit tests. Does not build the bundle first.."}
            ["shell" "./node_modules/karma/bin/karma" "start" "--single-run"]

           ; "test-js"                          ^{:doc "Test compiled JavaScript."}
           ; ["shell" "npm" "run" "test"]
            "test-js" ^{:doc "Run Unit Tests. Will compile bundle first."}
            ["do" "build-test" ["test-run"]]

            ; APP

            "webly"
            ["with-profile" "+demo" "run" "-m" "demo.app"]})
