(ns demo.core
  (:require
   [reagent.dom]
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
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
   [demo.app]
   ))


(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (reagent.dom/render [demo.app/app]
                      (.getElementById js/document "app")))

(defn ^:export init []
  (start))
