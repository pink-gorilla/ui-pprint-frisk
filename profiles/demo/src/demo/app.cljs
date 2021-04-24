(ns demo.app
  (:require
   [re-frame.core :as rf]
   [webly.user.app.app :refer [webly-run!]]
   ; gorilla-ui setuo
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
   [pinkgorilla.ui.css :refer [components config]]
   ; demo
   [demo.routes :refer [routes-api routes-app]]
   [demo.events] ; side-effects
   [demo.page.test] ; side-effects
   [demo.page.examples]
   ))


(rf/dispatch [:css/add-components components config])

(defn ^:export start []
  (webly-run! routes-api routes-app))