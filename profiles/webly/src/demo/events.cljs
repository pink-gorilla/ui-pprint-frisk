(ns demo.events
  (:require
   [taoensso.timbre :as timbre :refer [info]]
   [re-frame.core :refer [reg-event-db dispatch]]))

(reg-event-db
 :demo/start
 (fn [db [_]]
   (info "starting demo app..")
   (dispatch [:webly/status :running])

   db))

(reg-event-db
 :webly/before-load
 (fn [db [_]]
   (info "gorilla-ui reload..")

   db))




