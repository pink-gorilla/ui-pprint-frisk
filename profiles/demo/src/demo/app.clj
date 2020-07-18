(ns demo.app
  (:require
   [taoensso.timbre :as timbre :refer [info]]
   [webly.build :refer [build-cli]]
   [webly.config :refer [webly-config]]
   [webly.web.handler :refer [make-handler]]
   [demo.routes :refer [demo-routes-backend demo-routes-frontend]]))

(info "making handler ..")
(def handler (make-handler demo-routes-backend demo-routes-frontend))

(defn -main
  [mode]
  (info "demo starting mode: " mode)
  (swap! webly-config assoc :timbre-loglevel :info)
  (swap! webly-config assoc :title "gorilla-ui")
  (swap! webly-config assoc :start "demo.app.start (); ")

  (build-cli mode "+demo" "demo.app/handler" "demo.app"))