(ns example.math
  (:require
   [demo.example :as example]))

(example/add
 "js-math"
 [:div.bg-green-300 {:style {:width 300
                               :height 100}}
  [:p/math "(ax ^2 + bx + c = 0 )"]])
