(ns pinkgorilla.ui.data.cytoscape
  (:require
   [re-frame.core :as rf]
   ["cytoscape" :as c :refer [Cytoscape]]
   ["cytoscape-cose-bilkent" :as cs :refer [COSEBilkent]]
   ["react-cytoscapejs" :as CytoscapeComponent ;:refer [CytoscapeComponent]
    ]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.box :refer [box]]))

(println "cb:" cs)
(println "c:" c)
(.use c cs) ; COSEBilkent)

(defn ^{:category :data}
  cytoscape
  [data]
  [:> CytoscapeComponent data])

(register-component :p/cytoscape-raw cytoscape)

(defn ^{:category :data}
  cytoscape-boxed
  "reagent component to render highchart-spec via highcharts.js
   Usage:  [:p/highchart spec-as-clj-data]"
  [data]
  [box {:s :small
        :render cytoscape
        :data data}])

(register-component :p/cytoscape cytoscape-boxed)