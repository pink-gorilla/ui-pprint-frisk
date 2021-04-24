(ns example.data.sparklines
  (:require
   [example.example :as example]
   [pinkgorilla.ui.data.sparklines :refer [sparkline sparkline-bar sparkline-spot]]))

(defn data [nr]
  (let [d (vec (take nr  (repeatedly #(.round js/Math (* 100 (rand))))))
       ; _ (println "d is:" d)
        ]
    d))

(example/add
 :viz/sparklines
 [:div
  [:h1 "spark-line"]
  [:p/sparkline {:data (data 20) :limit 20 :width 100 :height 20 :margin 5 :svgWidth 100 :svgHeight 20}]
  [:p/sparkline {:data (data 40) :limit 40 :width 100 :height 20 :margin 5 :svgWidth 100 :svgHeight 20}]])

(example/add
 :viz/sparklines
 [:div
  [:h1 "spark-spot"]
  [:p/sparklinespot {:data (data 40) :limit 100 :svgWidth 100 :svgHeight 20 :margin 1}]])

(example/add
 :viz/sparklines
 [:div
  [:h1 "spark-bar"]
  [:p/sparklinebar {:data [5, 10, 5, 20, 10] :limit 5 :svgWidth 100 :svgHeight 20 :margin 5}]
  [:p/sparklinebar {:data (data 150) :limit 50
                    :svgWidth 300 :svgHeight 20
                    :margin 1}]])

