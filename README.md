# Pink Gorilla UI [![GitHub Actions status |pink-gorilla/gorilla-ui](https://github.com/pink-gorilla/gorilla-ui/workflows/CI/badge.svg)](https://github.com/pink-gorilla/gorilla-ui/actions?workflow=CI)[![Codecov Project](https://codecov.io/gh/pink-gorilla/gorilla-ui/branch/master/graph/badge.svg)](https://codecov.io/gh/pink-gorilla/gorilla-ui)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/gorilla-ui.svg)](https://clojars.org/org.pinkgorilla/gorilla-ui) 

- end users: this project is not for you!
- UI components targetting visualisation.
- to be used from reagent
- renderers can use npm (js/react) dependencies, or
  maven (cljs) dependencies

## Demo ui components with Goldly

Run `lein notebook` to run notebook with gorilla-ui notebooks. Navigate browser to port 8000.

Run `lein goldly` to run goldly with gorilla-ui snippets. This will run a web server at 
port 8000 with lots of examples of ui components. Navigate to snippets registry.

## Develop with gorilla-ui

Run `lein develop` to run the demo app at port 8000. 
This will run a webly build, some test pages of components 
that you want to improve.

Documentation: 

- gorilla-ui is a library
  We therefore do NOT bundle compiled javascript bundles.

- however, we ship static images and css files that we collect 
  from selected npm modules. This makes it easier to consume gorilla-ui library.

- we also ship deps.cljs, which tells shadow-cljs to add transient npm deps
  from gorilla-ui.


# embed

Run `lein embed` to see how to embed pinkie ui components in ordinary html pages.
