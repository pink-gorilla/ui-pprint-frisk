(ns pinkgorilla.ui.leaflet
  (:require
   [reagent.core :as r]
   ["react-leaflet" :refer [Map Marker TileLayer]]))

; <style>
;        .leaflet-container {
;          height: 400px;
;          width: 100%;
;        }
;    </style>


(defn mymap [marker-position]
  [:div-mymap
   [:style ".leaflet-container" "{width: 600px; height: 600px;}"]
   [:link {:rel "stylesheet" :href "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"}]
   [:> Map {:center marker-position
            :zoom 13}
    [:> TileLayer
     {:url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      :attribution "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"}]
    [:> Marker {:position marker-position}
   ; [Popup "A pretty CSS3 popup.<br />Easily customizable."]
     ]]])


