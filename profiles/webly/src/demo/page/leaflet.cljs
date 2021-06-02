(ns demo.page.leaflet
  (:require
   [reagent.core :as r]
    [webly.web.handler :refer [reagent-page]]
   [pinkgorilla.ui.control.select :refer [select-nav]]
  ))

(defonce state (r/atom {:location :london}))


(defmethod reagent-page :demo/leaflet [{:keys [route-params query-params handler] :as route}]
  [:<>
   [:h2 "select the map you like"]
   [select-nav {:items [:london :coronado] 
                :value (:location @state)
                :on-change (fn [x] (swap! state assoc-in [:location] x))}]
   [:p "state: " (pr-str @state)]
   ;(case (:location @state)
     ;:london  london
   ;  :coronado coronado)
   ])



