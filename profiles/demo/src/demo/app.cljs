(ns demo.app
  (:require
   [reagent.dom]
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
   [pinkgorilla.ui.config :refer [set-prefix!]]
   ; examples
   [example.pinkie]
   [example.controls]
   ; from renderable-ui
   [example.gtable]
   ; js based enderer
   [example.highchart]
   [example.json]
   [example.vega]
   [example.math]
   ; reagent based renderer
   [example.leaflet]
   [example.sparklines]
   [example.player]
   [example.aggrid]
   [example.clock]
   [example.gridlayout]
   [example.pydoc]
   ; demo
   [demo.views]))

(enable-console-print!)

(set-prefix! "/")

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (println "starting with println")
  (reagent.dom/render [demo.views/app]
                      (.getElementById js/document "app")))

(defn ^:export init []
  (start))
