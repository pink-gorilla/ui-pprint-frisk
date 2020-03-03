(ns pinkgorilla.ui.demo.sparklines
  (:require
   [pinkgorilla.ui.sparklines :refer [sparkline sparkline-bar sparkline-spot]]))

(defn data [nr]
  (let [d (vec (take nr (repeatedly rand)))
       ; _ (println "d is:" d)
        ]
    d))

(defn app []
  [:div.sparklines-container
   [sparkline {:data (data 20) :limit 20 :width 100 :height 20 :margin 5}]
   [sparkline {:data (data 40) :limit 40 :width 100 :height 20 :margin 5}]
   [sparkline-spot {:data (data 100) :limit 100 :svgWidth 100 :svgHeight 20 :margin 1}]
   [sparkline-bar {:data [5, 10, 5, 20, 10] :limit 5 :svgWidth 100 :svgHeight 20 :margin 5}]
   [sparkline-bar {:data (data 150) :limit 50
                   :svgWidth 300 :svgHeight 20
                   :margin 1}]])

