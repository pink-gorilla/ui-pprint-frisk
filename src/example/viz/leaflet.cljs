(ns example.viz.leaflet
  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.control.select :refer [select-map]]
   [example.example :as example]))

(defonce state (r/atom {:location :london}))

(defn demo []
  [:<>
   [:h2 "select the map you like"]
   [select-map {:nav? true} [:london :coronado] state :location]
   [:p "location: " @state]
   ;(case (:location @state)
     ;:london  london
   ;  :coronado coronado)
   ])

(example/add
 :cljs/leaflet
 [demo])

