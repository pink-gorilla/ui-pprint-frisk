(ns pinkgorilla.ui.demo-leaflet

  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.leaflet :refer [mymap]]
   ["react-leaflet" :refer [Map Marker TileLayer]]))

(def position [51.505, -0.09])

(defn app []
  ;[:div.leaflet-container
  [mymap position]
;   ]
  )

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
