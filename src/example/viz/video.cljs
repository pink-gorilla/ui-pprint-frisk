(ns example.viz.video
  (:require
   [example.example :as example]))

(example/add
 :viz/video
 [:div.w-full.h-screen ; .player-container
  [:p/video {:width "100%"
             :height "100%"
             :url "https://www.youtube.com/watch?v=Bs44qdAX5yo"}]])
