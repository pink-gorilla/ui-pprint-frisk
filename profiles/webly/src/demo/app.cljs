(ns demo.app
  (:require
   [re-frame.core :as rf]
   ; gorilla-ui setup
   [pinkgorilla.ui.default-renderer] ; add ui renderer definitions 
   [pinkgorilla.ui.css :refer [components config]]

   ; side-effects
   [demo.events]
   [demo.page.main]
   ))


(rf/dispatch [:css/add-components components config])

