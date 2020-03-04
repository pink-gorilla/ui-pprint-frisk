(ns pinkgorilla.ui.demo.core
  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [print-registered-tags]]
   [pinkgorilla.ui.demo.leaflet]
   [pinkgorilla.ui.demo.sparklines]
   [pinkgorilla.ui.demo.player]
   [pinkgorilla.ui.demo.aggrid]))

(def app
  [:<>
   (print-registered-tags)
   [pinkgorilla.ui.demo.leaflet/app]
   [pinkgorilla.ui.demo.sparklines/app]
   [pinkgorilla.ui.demo.player/app]
   [pinkgorilla.ui.demo.aggrid/app]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (js/console.log (print-registered-tags))
  (r/render app
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
