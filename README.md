# Pink Gorilla UI [![GitHub Actions status |pink-gorilla/gorilla-ui](https://github.com/pink-gorilla/gorilla-ui/workflows/CI/badge.svg)](https://github.com/pink-gorilla/gorilla-ui/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/gorilla-ui.svg)](https://clojars.org/org.pinkgorilla/gorilla-ui) 

- UI components targetting visualisation.
- to be used from reagent
- renderers can use npm (js/react) dependencies, or
  maven (cljs) dependencies

## Demo:

Run `npm install` to download dependencies.

Run `lein demo` to run the demo app.

Run `lein embed` to see how to embed pinkie ui components in ordinary html pages.


## Dev

Note that dependencies are declared twice - in shadow-cljs.edn and project.clj

If there are problems in using vega with errors to "buffer" then do
this in your project: 
```
npm install shadow-cljs --save
```
thheller: both buffer and process are polyfills packages that shadow-cljs will provide ... the npm package is mostly the for CLI stuff but also brings in some extra npm packages
the compiler is from the CLJ dependency you have in project.clj.
the npm stuff never does any actual compilation, just runs the java process

## Tailwind CSS

- https://umeshmk.github.io/Tailwindcss-cheatsheet/
- https://mertjf.github.io/tailblocks/
- https://merakiui.com/
- https://www.creative-tim.com/learning-lab/tailwind-starter-kit/presentation
- https://tailwindcomponents.com/
- http://www.heropatterns.com/
