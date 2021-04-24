(ns demo.app
  (:require
   [re-frame.core :as rf]
   [webly.user.app.app :refer [webly-run!]]
   [webly.web.handler :refer [reagent-page]]
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
   [pinkgorilla.ui.css :refer [components config]]
   [example.core :refer [example-component]]
   ; demo
   [demo.routes :refer [routes-api routes-app]]
   [demo.events] ; side-effects
   [pinkie.error :refer [error-boundary]]
   [example.cytoscape :refer [c-ex]]
   ))

(defmethod reagent-page :demo/test [& args]
  ;[error-boundary
  [c-ex])

(defmethod reagent-page :demo/main [& args]
  [example-component])

(defmethod reagent-page :demo/viz [& args]
  (let [nsf (namespace :viz/all)]
    ;(println "nsf: " nsf)
    [example-component nsf]))

(rf/dispatch [:css/add-components components config])

(defn ^:export start []
  (webly-run! routes-api routes-app))