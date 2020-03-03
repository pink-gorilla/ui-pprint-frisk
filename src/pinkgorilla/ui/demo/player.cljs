(ns pinkgorilla.ui.demo.player
  (:require
   [pinkgorilla.ui.player :refer [player]]))

(defn app []
  [:div.player-container
   [player "https://www.youtube.com/watch?v=Bs44qdAX5yo"]])
