(ns pinkgorilla.ui.viz.cytoscape
  (:require
   [re-frame.core :as rf]
   ["cytoscape" :as cytoscope]
   ["cytoscape-cose-bilkent" :as cose-bilkent]
   ["cytoscape-dagre" :as dagre]
   ["react-cytoscapejs" :as CytoscapeComponent]
   [pinkgorilla.ui.box :refer [box]]))

(.use cytoscope cose-bilkent)
(.use cytoscope dagre);

(defn ^{:category :data}
  cytoscape
  [data]
  [:> CytoscapeComponent data])

(defn ^{:category :data}
  cytoscape-boxed
  ""
  [data]
  #_[box {:s :small
          :render cytoscape
          :data data}]
  ;[:div {:style {:width "600px" :max-width "600px" :height "500px"}}
  [cytoscape (merge data
                    {:style {:width "600px" :max-width "600px" :height "500px"}})]

   ;]
  )



; https://js.cytoscape.org/
; https://github.com/plotly/react-cytoscapejs


; other options:
; http://www.graphviz.org/
; geschichte