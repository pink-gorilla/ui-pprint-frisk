(ns pinkgorilla.ui.ui.json
  "custom components need to define a javascript-module
   module-test is a very simple sample that just renders the data as
   JSON to the dom node that is passed.
   In early state component development this is helpful."
  (:require
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.jsrender :refer [render-js]]))

(defn render-json [dom-node data-js]
  (let [data-json (.stringify js/JSON data-js)
        text-node (.createTextNode js/document data-json)
        p (.appendChild (.createElement js/document "p") text-node)
        _ (.appendChild dom-node p)]))

(defn ^{:category :ui}
  json
  "renders data formatted as json 
   (helpful to export data or debugging)"
  [data-clj]
  [render-js {:f render-json :data data-clj}])

(register-component :p/json json)

