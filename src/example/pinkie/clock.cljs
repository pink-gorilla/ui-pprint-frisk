(ns example.pinkie.clock
  (:require [example.example :as example]))

(example/add
 :gorilla/clock
 [:div ; .clock-demo
  [:p/clock]])
