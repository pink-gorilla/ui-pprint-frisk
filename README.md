# Pink Gorilla UI [![GitHub Actions status |pink-gorilla/gorilla-ui](https://github.com/pink-gorilla/gorilla-ui/workflows/CI/badge.svg)](https://github.com/pink-gorilla/gorilla-ui/actions?workflow=CI)[![Codecov Project](https://codecov.io/gh/pink-gorilla/gorilla-ui/branch/master/graph/badge.svg)](https://codecov.io/gh/pink-gorilla/gorilla-ui)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/gorilla-ui.svg)](https://clojars.org/org.pinkgorilla/gorilla-ui) 

- UI components targetting visualisation.
- to be used from reagent
- renderers can use npm (js/react) dependencies, or
  maven (cljs) dependencies

## Demo

Run `npm install` to download npm dependencies.

Run `lein demo` to run the demo app. This will run a web server at port 8000 with lots of examples of ui components.

Run `lein embed` to see how to embed pinkie ui components in ordinary html pages.


## Dev

Documentation: Run lein demo .. this showcases all components.

Add a dependency to your clojurescript project, and you are good to go. Note that we use shadow-cljs which allows easy management of npm dependencies.

If there are problems in using vega with errors to "buffer" then `npm install shadow-cljs --save` might fix it. thheller: both buffer and process are polyfills packages that shadow-cljs will provide ... the npm package is mostly the for CLI stuff but also brings in some extra npm packages
the compiler is from the CLJ dependency you have in project.clj.
the npm stuff never does any actual compilation, just runs the java process

## Tailwind CSS

- https://umeshmk.github.io/Tailwindcss-cheatsheet/
- https://mertjf.github.io/tailblocks/
- https://merakiui.com/
- https://www.creative-tim.com/learning-lab/tailwind-starter-kit/presentation
- https://tailwindcomponents.com/
- http://www.heropatterns.com/
