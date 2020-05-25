(ns example.player
  (:require
   [demo.example :as example]))

(example/add
 "player"
  [:div.player-container
   [:p/player "https://www.youtube.com/watch?v=Bs44qdAX5yo"]])
