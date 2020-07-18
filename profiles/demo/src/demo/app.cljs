(ns demo.app
  (:require
   ;[reagent.dom]
   [taoensso.timbre :as timbre :refer [info]]
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 

   [pinkgorilla.ui.config :refer [set-prefix!]]
   [webly.web.app]
   [webly.config :refer [webly-config]]

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
   [demo.routes :refer [demo-routes-backend]]
   [demo.views]))

(defn ^:export start []
  (swap! webly-config assoc :timbre-loglevel :info)
  (info "gorilla-ui demo starting ..")
  (webly.web.app/start demo-routes-backend)
  (webly.web.app/mount-app))



;(enable-console-print!)

;(set-prefix! "/")

#_(defn stop []
    (js/console.log "Stopping .."))

#_(defn start []
    (js/console.log "Starting gorilla-ui ...")
    (println "starting with println")
    (reagent.dom/render [demo.views/app]
                        (.getElementById js/document "app")))

#_(defn ^:export init []
    (start))
