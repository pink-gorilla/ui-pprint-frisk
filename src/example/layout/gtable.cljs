(ns example.layout.gtable
  (:require
   [example.example :as example]))

(def data [[:p "hi"] [:p "ho"]])

(example/add
 :layout/gtable
 [:div.table-demo
  [:p/gtable (repeat 3 data)]])