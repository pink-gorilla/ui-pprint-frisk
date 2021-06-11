(ns demo.page.leaflet
  (:require
   [reagent.core :as r]
   [webly.web.handler :refer [reagent-page]]
   [pinkgorilla.input.bind :refer [bind]]
   [pinkgorilla.input.select :refer [select-nav]]
   [pinkgorilla.ui.viz.leaflet :refer [leaflet-map]]))

(def select-a (bind select-nav))

(defonce state (r/atom {:location :london}))


(def places
  {:london [51.49 -0.08]
   :panama [8.5387424 -79.8837403]
   :vienna [48.2215786 16.2684791]})

(defmethod reagent-page :demo/leaflet [{:keys [route-params query-params handler] :as route}]
  (let [spec {:zoom 12
              :height 600
              :width 700
              :center (get places (:location @state))
              :features []}]
    [:<>
     [:h2 "select the map you like"]
     [select-a {:items (keys places)} state [:location]]
     [:p "state: " (pr-str @state)]
     [:div "spec: " (pr-str spec)]
     (when (:location @state)
       [leaflet-map spec])]))



