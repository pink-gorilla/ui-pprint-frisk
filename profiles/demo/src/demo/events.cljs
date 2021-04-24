(ns demo.events
  (:require
   [taoensso.timbre :as timbre :refer [info]]
   [re-frame.core :refer [reg-event-db dispatch]]
   [example.example :refer [examples]]
   ))

(reg-event-db
 :demo/start
 (fn [db [_]]
   (info "starting demo app..")
   (dispatch [:ga/event {:category "webly-demo" :action "started" :label 77 :value 13}])
   ; simulate a slow bundle load time, so we can see the ui
   (.setTimeout js/window (fn []
                            (info "gorilla-ui demo started.")
                            (dispatch [:webly/status :running])) 100)

   db))

(reg-event-db
 :webly/before-load
 (fn [db [_]]
   (info "gorilla-ui reload..")
   (reset! examples {})
   db
   ))




