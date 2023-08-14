# ui-pprint-frisk [![GitHub Actions status |pink-gorilla/ui-pprint-frisk](https://github.com/pink-gorilla/ui-pprint-frisk/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-pprint-frisk/actions?workflow=CI)[![Codecov Project](https://codecov.io/gh/pink-gorilla/ui-pprint-frisk/branch/master/graph/badge.svg)](https://codecov.io/gh/pink-gorilla/ui-pprint-frisk)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-pprint-frisk.svg)](https://clojars.org/org.pinkgorilla/ui-pprint-frisk) 

- end users: this project is not for you!

- UI components:
  - pprint (pprint-table)
  - frisk (datastructure navigation)


## Demo 

Run `clojure -X:docs` to run goldly-docs with ui-gorilla. 
This will run a web server at port 8080. 


## developers

```
clojure -X:docs:build :profile "npm-install"
clojure -X:docs:build :profile "compile2"
clojure -X:docs

```
