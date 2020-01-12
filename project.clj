(defproject org.pinkgorilla/gorilla-ui "0.1.3-SNAPSHOT"
  :license {:name "MIT"}
  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/release_username
                                     :password :env/release_password
                                     :sign-releases false}]]
  ;; :source-paths ["src"]
  
  :plugins [[lein-shell "0.5.0"]]

  :dependencies [[reagent "0.8.1"]
                 [thi.ng/strf "0.2.2"]
                 [org.pinkgorilla/gorilla-renderable-ui "0.1.0"]
                 [thheller/shadow-cljsjs "0.0.21"]]
  
  :profiles {:dev {:dependencies [[thheller/shadow-cljs "2.8.80"]
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
  :aliases {;; "build-shadow-ci" ["run" "-m" "shadow.cljs.devtools.cli" "compile" ":ci"]
            "shadow-watch-demo" ["run" "-m" "shadow.cljs.devtools.cli" "watch" ":demo"]
            "test-js"                          ^{:doc "Test compiled JavaScript."}
            ["shell" "npm" "run" "test"]
            "bump-version" ["change" "version" "leiningen.release/bump-version"]}

  :release-tasks [["vcs" "assert-committed"]
                  ["bump-version" "release"]
                  ["vcs" "commit" "Release %s"]
                  ["vcs" "tag" "v" "--no-sign"]
                  ["deploy"]
                  ["bump-version"]
                  ["vcs" "commit" "Begin %s"]
                  ["vcs" "push"]])