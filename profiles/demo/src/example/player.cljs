(ns example.player
  (:require
   [demo.example :as example]))

(example/add
 "player"
  [:div.w-full.h-screen ; .player-container
   [:p/player {:width "100%"
               :height "100%"
               :url "https://www.youtube.com/watch?v=Bs44qdAX5yo"}]])
