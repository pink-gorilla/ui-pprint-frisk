(ns example.viz.frisk
  (:require
   [example.example :as example]))

(example/add
 :viz/frisk
 [:div
  [:p/frisk {:a "I'm a string"
             :b :imakeyword
             :c [1 2 3]
             :d '(1 2 3)
             :e #{1 2 3}
             :f (clj->js {:i-am "an-object"})
             "g" "String key"
             0 nil
             "not a number" js/NaN}]
  [:p "Frisk Demo end"]])