(ns example.math
  (:require
   [example.example :as example]))

(example/add
 :viz/math
 [:div.bg-green-300 {:style {:width 300
                             :height 100}}
  [:p/math "(ax ^2 + bx + c = 0 )"]])
