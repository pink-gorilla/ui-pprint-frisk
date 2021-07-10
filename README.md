# Pink Gorilla UI [![GitHub Actions status |pink-gorilla/ui-gorilla](https://github.com/pink-gorilla/ui-gorilla/workflows/CI/badge.svg)](https://github.com/pink-gorilla/ui-gorilla/actions?workflow=CI)[![Codecov Project](https://codecov.io/gh/pink-gorilla/ui-gorilla/branch/master/graph/badge.svg)](https://codecov.io/gh/pink-gorilla/ui-gorilla)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/ui-gorilla.svg)](https://clojars.org/org.pinkgorilla/ui-gorilla) 

- end users: this project is not for you!
- UI components targetting visualisation.
- to be used from reagent
- renderers can use npm (js/react) dependencies, or
  maven (cljs) dependencies

## Demo ui components with Goldly

Run `lein notebook` to run notebook with ui-gorilla notebooks. Navigate browser to port 8000.

Run `lein goldly watch` to run goldly with ui-gorilla snippets. This will run a web server at 
port 8000 with lots of examples of ui components. Navigate to snippets registry.

## Develop with ui-gorilla

Run `lein develop` to run the demo app at port 8000. 
This will run a webly build, some test pages of components 
that you want to improve.

Documentation: 

- we ship static images and css files that we collect 
  from selected npm modules. This makes it easier to consume ui-gorilla library.


# embed

Run `lein embed` to see how to embed pinkie ui components in ordinary html pages.
