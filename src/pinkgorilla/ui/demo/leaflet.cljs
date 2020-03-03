(ns pinkgorilla.ui.demo.leaflet
  (:require
   [pinkgorilla.ui.leaflet :refer [mymap]]
   ["react-leaflet" :refer [Map Marker TileLayer]]))

(def position [51.505, -0.09])

(defn app []
  ;[:div.leaflet-container
  [mymap position]
;   ]
  )
