# Pink Gorilla UI [![GitHub Actions status |pink-gorilla/gorilla-ui](https://github.com/pink-gorilla/gorilla-ui/workflows/CI/badge.svg)](https://github.com/pink-gorilla/gorilla-ui/actions?workflow=CI)[![Codecov Project](https://codecov.io/gh/pink-gorilla/gorilla-ui/branch/master/graph/badge.svg)](https://codecov.io/gh/pink-gorilla/gorilla-ui)[![Clojars Project](https://img.shields.io/clojars/v/org.pinkgorilla/gorilla-ui.svg)](https://clojars.org/org.pinkgorilla/gorilla-ui) 

- end users: this project is not for you!
- UI components targetting visualisation.
- to be used from reagent
- renderers can use npm (js/react) dependencies, or
  maven (cljs) dependencies

## Demo

Run `lein demo` to run the demo app. This will run a web server at port 8000 with lots of examples of ui components.

Run `lein embed` to see how to embed pinkie ui components in ordinary html pages.

## Tailwind CSS

Tailwind css makes it really easy to build custom ui.

- https://umeshmk.github.io/Tailwindcss-cheatsheet/
- https://mertjf.github.io/tailblocks/
- https://merakiui.com/
- https://www.creative-tim.com/learning-lab/tailwind-starter-kit/presentation
- https://tailwindcomponents.com/
- http://www.heropatterns.com/
- htmp 2 hiccup: useful to convert tailwind html templates
  http://html2hiccup.buttercloud.com/

## reagent 
- https://blog.ducky.io/clojurescript/reagent/2016/05/11/reagent-dynamic-children/
- - http://pschwarz.bicycle.io/json-to-edn/   json->edn converter 

## Develop with gorilla-ui

Documentation: 

- gorilla-ui is a library
  We therefore do NOT bundle compiled javascript bundles.

- however, we ship static images and css files that we collect 
  from selected npm modules. This makes it easier to consume gorilla-ui library.

- we also ship deps.cljs, which tells shadow-cljs to add transient npm deps
  from gorilla-ui.

If there are problems in using vega with errors to "buffer" then `npm install shadow-cljs --save` might fix it. thheller: both buffer and process are polyfills packages that shadow-cljs will provide ... the npm package is mostly the for CLI stuff but also brings in some extra npm packages
the compiler is from the CLJ dependency you have in project.clj.
the npm stuff never does any actual compilation, just runs the java process

you do not need to worry about process or buffer at all
you can fix this easily by bumping 
:compiler-options {:output-feature-set :es6} or whatever language level is appropriate
:es8 is good if you have bunch of async/await code in libs