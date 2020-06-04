(ns pinkgorilla.ui.sparklines
  (:require
   ["react-sparklines" :as rs :refer [Sparklines SparklinesLine SparklinesBars SparklinesSpots]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn sparkline [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]])

(defn sparkline-bar [data]
  [:> Sparklines data
   [:> SparklinesBars {:color "blue"}]])

(defn sparkline-spot [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]
   [:> SparklinesSpots {:color "blue"}]])

(register-tag :p/sparkline sparkline)
(register-tag :p/sparklinebar sparkline-bar)
(register-tag :p/sparklinespot sparkline-spot)