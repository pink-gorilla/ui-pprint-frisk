(ns demo.app
  (:require
   [webly.user.app.app :refer [webly-run!]]
   [demo.routes :refer [routes-api routes-app]]

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
   [demo.views]
   [demo.events]
   ))

(defn ^:export start []
  (webly-run! routes-api routes-app))