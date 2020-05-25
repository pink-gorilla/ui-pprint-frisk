(ns pinkgorilla.ui.math
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   ;["mathjax" :as mathjax]
   ["@nteract/mathjax" :refer [Provider Node]]))

(println "mathjax: " mathjax)

(def options {:messageStyle "none"})


(defn math [data-clj]
  [:> Provider options
    [:p data-clj]
   [:> Node 
    data-clj]
   [:> Node "a = b"]
   ])

(register-tag :p/math math)




