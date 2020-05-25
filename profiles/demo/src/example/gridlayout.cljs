(ns example.gridlayout
  (:require
   [demo.example :as example]))

(def layout [{:i "a" :x 0 :y 0 :w 1 :h 2 :static true}
             {:i "b" :x 1 :y 0 :w 3 :h 2 :minW 2 :maxW 4}
             {:i "c" :x 4 :y 0 :w 1 :h 2}])

(example/add
 "gridlayout"
  [:p/gridlayout {:className "layout"
                  :layout layout
                  :cols 12
                  :rowHeight 30
                  :width 1200}
   [:div.bg-blue-700 {:key "a"} "a"]
   [:div.bg-orange-300 {:key "b"} "b"]
   [:div.bg-green-600 {:key "c"} "c"]])
