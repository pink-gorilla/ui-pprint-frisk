(defproject org.pinkgorilla/gorilla-ui "0.3.36-SNAPSHOT"
  :description "Reagent components for data visualisation."
  :url "https://github.com/pink-gorilla/gorilla-ui"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]

  :min-lein-version "2.9.4" ; nrepl 0.7.0
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
                    "target/node_modules"] ; css png resources from npm modules
  :clean-targets ^{:protect false} [:target-path
                                    [:demo :builds :app :compiler :output-dir]
                                    [:demo :builds :app :compiler :output-to]]

  :dependencies [[thi.ng/strf "0.2.2"
                  :exclusions [org.clojure/clojurescript]]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"] ;; awb99: in encoding, and clj/cljs proof
                 [fipp "0.6.23"] ; edn pretty printing - for examples (examples get shipped)
                 [lambdaisland/ansi "0.1.6"]
                 [org.pinkgorilla/pinkie "0.3.3"]]

  :managed-dependencies [; conflict resolution for notebook
                         [borkdude/sci "0.2.5"]
                         [com.fasterxml.jackson.core/jackson-core "2.12.3"]
                         [cljs-ajax "0.8.3"]]

  :profiles {:test {:test-paths   ["test"]}
             :embed {:source-paths ["profiles/embed/src"
                                    "test"]}

             :goldly {:dependencies [[org.clojure/clojure "1.10.3"]
                                     [org.pinkgorilla/ui-repl "0.0.9"]
                                     [org.pinkgorilla/ui-input "0.0.2"]
                                     [org.pinkgorilla/goldly "0.2.89"]]
                      ;:resource-paths ["target/webly"]
                      } ; bundle

             :notebook {:dependencies [[org.clojure/clojure "1.10.3"]
                                       [org.pinkgorilla/notebook "0.5.32"]]}

             :webly {:dependencies [[org.clojure/clojure "1.10.3"]
                                    [org.pinkgorilla/webly "0.3.1"]
                                    [org.pinkgorilla/ui-input "0.0.2"]
                                    ]
                     :resource-paths  ["profiles/webly/resources"
                                       "target/webly"] ; bundle
                     :source-paths ["src"
                                    "profiles/webly/src"
                                    "test"]}
             :dev  {:dependencies [[org.clojure/clojure "1.10.3"]
                                   ; shadow-cljs MAY NOT be a dependency in lein deps :tree -> if so, bundeler will fail because shadow contains core.async which is not compatible with self hosted clojurescript
                                   [clj-kondo "2021.04.23"]]
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

            "gorilla-ui"  
            ["with-profile" "-dev,+webly" "run" "-m" "webly.user.app.app" "webly-gorillaui.edn"]

            "build-test"  ^{:doc "Builds Bundle. Gets executed automatically before unit tests."}
            ["gorilla-ui" "ci"]

            "test-run" ^{:doc "Runs unit tests. Does not build the bundle first.."}
            ["shell" "npm" "test"]

            "test-js" ^{:doc "Run Unit Tests. Will compile bundle first."}
            ["do" "build-test" ["test-run"]]

            ; APP

            "develop"
            ["gorilla-ui" "watch"]

            "goldly"
            ["with-profile" "-dev,+goldly" ; dev is excluded because clj-kondo has old sci
             "run" "-m" "goldly-server.app"
             "goldly-gorillaui.edn"
             "watch"]

            "notebook"
            ["with-profile" "+notebook"
             "run" "-m" "pinkgorilla.notebook-ui.app.app"
             "notebook-gorillaui.edn"
             "watch"]

            ;
            })
