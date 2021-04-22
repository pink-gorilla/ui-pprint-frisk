(ns example.clock
  (:require [example.example :as example]))

(example/add
 :gorilla/clock
 [:div ; .clock-demo
  [:p/clock]])
