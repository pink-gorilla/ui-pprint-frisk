(ns example.viz.math
  (:require
   [example.example :as example]))

(example/add
 :viz/math
 [:div.inline-block.bg-green-300.p-2.box-border #_{:style {:width 300
                                                           :height 100}}
  [:p/math "(ax ^2 + bx + c = 0 )"]])