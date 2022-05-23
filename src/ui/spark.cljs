(ns ui.spark
  (:require
   ["react-sparklines" :as rs :refer [Sparklines SparklinesLine SparklinesBars SparklinesSpots]]))

; https://www.npmjs.com/package/react-sparklines

(defn ^{:category :data}
  sparkline
  "displays sparkline line-plot
   example:
    [:p/sparkline {:data [1 2 3 4 5] :limit 20 :width 100 :height 20 :margin 5 :svgWidth 100 :svgHeight 20}]
   "
  [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]])

(defn ^{:category :data}
  sparkline-bar
  "displays sparkline bar-plot
   example:
     [:p/sparklinebar {:data [5, 10, 5, 20, 10] :limit 5 :svgWidth 100 :svgHeight 20 :margin 5}]
   "
  [data]
  [:> Sparklines data
   [:> SparklinesBars {:color "blue"}]])

(defn ^{:category :data}
  sparkline-spot
  "displays sparkline bar-plot
   example:
    [:p/sparklinespot {:data [1 5 77 4 55] :limit 100 :svgWidth 100 :svgHeight 20 :margin 1}]
  "
  [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]
   [:> SparklinesSpots {:color "blue"}]])
