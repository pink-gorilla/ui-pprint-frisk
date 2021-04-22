(ns demo.app
  (:require
   [webly.user.app.app :refer [webly-run!]]
   [webly.web.handler :refer [reagent-page]]
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
   [example.core :refer [example-component]]
   ; demo
   [demo.routes :refer [routes-api routes-app]]
   [demo.events] ; side-effects
   ))

(defmethod reagent-page :demo/main [& args]
  [example-component])

(defmethod reagent-page :demo/viz [& args]
  (let [nsf (namespace :viz/all)]
    (println "nsf: " nsf)
    [example-component nsf]))


(defn ^:export start []
  (webly-run! routes-api routes-app))