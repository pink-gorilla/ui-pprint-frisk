(ns pinkgorilla.ui.demo-player
  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.player :refer [player]]))

(defn app []
  [:div.player-container
   [player "https://www.youtube.com/watch?v=Bs44qdAX5yo"]])

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
