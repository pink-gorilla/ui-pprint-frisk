(defproject org.pinkgorilla/gorilla-ui "0.2.34-SNAPSHOT"
  :description "Reagent components for data visualisation."
  :url "https://github.com/pink-gorilla/gorilla-ui"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]

  :min-lein-version "2.9.4" ; nrepl 0.7.0

  :prep-tasks [;"javac"
               ;"compile" - aot
               "resource"
               "css"
               ;"shadowcompile2"
               ]

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

  :target-path  "target/jar"
  :clean-targets ^{:protect false} [:target-path
                                    [:demo :builds :app :compiler :output-dir]
                                    [:demo :builds :app :compiler :output-to]]

  :resource-paths  ["resources" ; not from npm
                    "target/webly" ; bundle
                    "target/node_modules"] ; css png resources from npm modules


  :resource {:silent false
             :resource-paths [["node_modules/tailwindcss/dist"
                               {:includes [#".*"]
                                :target-path "target/node_modules/public/tailwindcss/dist"}]
                              ; font awesome from webly
                              #_["node_modules/@fortawesome/fontawesome-free/css"
                                 {:includes [#".*\.css"]
                                  :target-path "target/node_modules/public/@fortawesome/fontawesome-free/css"}]
                              ; this does ot work - resource plugin fucks up copied files
                              #_["node_modules/@fortawesome/fontawesome-free/webfonts"
                                 {:includes [#".*"]
                                  :target-path "target/node_modules/public/@fortawesome/fontawesome-free/webfonts"}]

                              ["node_modules/leaflet/dist"
                               {:includes [#".*\.css"] ;  #".*\.png"  png copy gets destroyed
                                :target-path "target/node_modules/public/leaflet/dist"}]
                              ["node_modules/ag-grid-community/dist/styles"
                               {:includes [#".*\.css"]
                                :target-path "target/node_modules/public/ag-grid-community/dist/styles"}]
                              ;["node_modules/highlight.js/styles"
                              ; {:includes [#".*\.css"]
                              ;  :target-path "target/node_modules/public/highlight.js/styles"}]
                              ;  http://localhost:8000/highlight.js/styles/github.css
                              ["node_modules/react-grid-layout/css"
                               {:includes [#".*\.css"]
                                :target-path "target/node_modules/public/react-grid-layout/css/"}]
                              ["node_modules/react-resizable/css"
                               {:includes [#".*\.css"]
                                :target-path "target/node_modules/public/react-resizable/css/"}]]}

  :managed-dependencies [[org.clojure/data.json "1.0.0"]
                         [com.fasterxml.jackson.core/jackson-core "2.11.2"]
                         [com.cognitect/transit-cljs "0.8.264"]
                         [com.cognitect/transit-clj "1.0.324"]
                         [com.cognitect/transit-java "1.0.343"]
                         [org.apache.httpcomponents/httpcore "4.4.13"]
                         [com.google.javascript/closure-compiler-unshaded "v20200719"]
                         [org.apache.httpcomponents/httpasyncclient "4.1.4"]
                         [commons-codec "1.14"]
                         [com.google.code.findbugs/jsr305 "3.0.2"]
                         [javax.xml.bind/jaxb-api "2.3.1"]
                          [org.ow2.asm/asm "8.0.1"]
                         ]


  :dependencies [; gorilla-ui is a cljs project, so in here are cljs dependencies
                 [org.clojure/clojurescript "1.10.773"]
                 [thi.ng/strf "0.2.2"
                  :exclusions [org.clojure/clojurescript]]
                 ;[com.taoensso/timbre "4.10.0"] ; clojurescript logging awb99: this fucks up kernel-cljs-shadowdeps
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"] ;; awb99: in encoding, and clj/cljs proof
                 [org.pinkgorilla/pinkie "0.2.11"]]

  :profiles {:test {:source-paths ["src" "test"]
                    :test-paths   ["test"]}
             :demo {:source-paths ["src"
                                   "profiles/demo/src"
                                   "profiles/embed/src"
                                   "test"]}
             :dev  {:dependencies [[org.clojure/clojure "1.10.1"]
                                   ; shadow-cljs MAY NOT be a dependency in lein deps :tree -> if so, bundeler will fail because shadow contains core.async which is not compatible with self hosted clojurescript
                                   [org.pinkgorilla/webly "0.0.25"] ; brings shadow-cljs
                                   [thheller/shadow-cljsjs "0.0.21"]
                                   [clj-kondo "2020.07.29"]]
                    :plugins      [[lein-cljfmt "0.6.6"]
                                   [lein-cloverage "1.1.2"]
                                   [lein-resource "17.06.1"]
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

             ;"build-shadow-ci" ["run" "-m" "shadow.cljs.devtools.cli" "compile" ":demo"] ; :ci
            ;"shadow-watch-demo" ["run" "-m" "shadow.cljs.devtools.cli" "watch" ":demo"]

            ;; TEST

            "build-test"  ^{:doc "Builds Bundle. Gets executed automatically before unit tests."}
            ["with-profile" "+test" "run" "-m" "shadow.cljs.devtools.cli" "compile" "ci"]

            "test-run" ^{:doc "Runs unit tests. Does not build the bundle first.."}
            ["shell" "./node_modules/karma/bin/karma" "start" "--single-run"]

           ; "test-js"                          ^{:doc "Test compiled JavaScript."}
           ; ["shell" "npm" "run" "test"]
            "test-js" ^{:doc "Run Unit Tests. Will compile bundle first."}
            ["do" "build-test" ["test-run"]]

            ;; SHADOW - APP

            "shadow-demo"  ^{:doc "Runs UI components via webserver."}
            ["with-profile" "+demo" "run" "-m" "shadow.cljs.devtools.cli" "watch" "webly"]

            "embed"  ^{:doc "Runs UI embedding via webserver."}
            ["with-profile" "+demo" "run" "-m" "shadow.cljs.devtools.cli" "watch" "embed"]

            ; APP

            "build-dev"  ^{:doc "compiles bundle via webly"}
            ["with-profile" "+demo" "run" "-m" "webly.build-cli" "compile" "+demo" "demo.app/handler" "demo.app"]

            "build-prod"  ^{:doc "compiles bundle via webly"}
            ["with-profile" "+demo" "run" "-m" "webly.build-cli" "release" "+demo" "demo.app/handler" "demo.app"]

            "run-web"  ^{:doc "runs compiles bundle on shadow dev server"}
            ["with-profile" "+demo" "run" "-m" "demo.app" "run"]

            "demo"  ^{:doc "Runs UI components via webserver."}
            ["with-profile" "+demo" "run" "-m" "demo.app" "watch"]})
