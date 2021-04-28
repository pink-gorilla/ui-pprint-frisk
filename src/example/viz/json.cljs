(ns example.viz.json
  (:require
   [example.example :as example]))

(def data
  {:title "Economic Activity"
   :subtitle "made with Love"
   :labels ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug"]
   :series
   [{:name "Installation" :data [439 523 577 698 931 1131 1333 1175]}
    {:name "Manufacturing" :data [249 244 292 291 390 302 381 404]}
    {:name "Sales & Distribution" :data [117 172 165 191 285 247 321 393]}]})

(example/add
 :viz/json
 [:p/json data])
