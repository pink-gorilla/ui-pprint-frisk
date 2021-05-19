(ns example.viz.cytoscape
  (:require
   [pinkgorilla.ui.viz.cytoscape :refer [cytoscape]]
   [pinkgorilla.ui.box :refer [size]]))

(def stylesheet
  [{:selector "node"
    :style {:width 20
            :height 20
            :shape "rectangle"}}
   {:selector "edge"
    :style {:width 15}}])

(def layout-random {:name "random"})

(def el [{:data {:id "a" :label "apple"} :position {:x 0 :y 0}}
         {:data {:id "b" :label "banana"} :position {:x 100 :y 0}}
         {:data {:id "c" :label "cherry"} :position {:x 200 :y 0}}])

(defn c-ex []
  (println "cx: " cytoscape)
  [:div.bg-blue-300 (size :medium)
   [cytoscape   {:stylesheet stylesheet
                 :elements el
                 :layout layout-random
                 :style {:border "1px solid #ccc"
                         :width "400px"
                         :height "400px"}}]])


