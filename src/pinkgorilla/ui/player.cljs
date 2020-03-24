(ns pinkgorilla.ui.player
  (:require
   [reagent.core :as r]
   ["react-player" :as rp :refer [ReactPlayer]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn player [url]
  [:div-player
  ;[:link {:rel "stylesheet" :href "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"}]
   [:> rp/default {:url url :playing true}]])

(register-tag :player player)