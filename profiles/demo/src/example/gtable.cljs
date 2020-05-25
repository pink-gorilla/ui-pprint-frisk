(ns example.gtable
  (:require
   [demo.example :as example]))

(def data [[:p "hi"] [:p "ho"]])

(example/add
 "gtable"
  [:div.table-demo
   [:p/gtable (repeat 3 data)]])