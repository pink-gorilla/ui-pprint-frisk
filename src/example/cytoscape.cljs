(ns example.cytoscape
  (:require
   [example.example :as example]
   [pinkgorilla.ui.data.cytoscape :refer [cytoscape]]
   [pinkgorilla.ui.size :refer [size]]))

(def stylesheet
  [{:selector "node"
    :style {:width 20
            :height 20
            :shape "rectangle"}}
   {:selector "edge"
    :style {:width 15}}])

(def layout-random {:name "random"})
(def layout-cose {:name "cose-bilkent"})

(def el [{:data {:id "one" :label "Node 1"} :position {:x 0 :y 0}}
         {:data {:id "two" :label "Node 2"} :position {:x 100 :y 0}}
         {:data {:source "one" :target "two" :label "Edge from Node1 to Node2"}}])

(defn c-ex []
  (println "cx: " cytoscape)
  [:div.bg-blue-300 (size :medium)
   [cytoscape   {:stylesheet stylesheet
                 :elements el
                 :layout layout-random}]])

(example/add
 :viz/cytoscape
 [cytoscape (clj->js  {;:stylesheet stylesheet
                       :elements el
                 ;:layout layout-random
                       })])
