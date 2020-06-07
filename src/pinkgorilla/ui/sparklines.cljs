(ns pinkgorilla.ui.sparklines
  (:require
   ["react-sparklines" :as rs :refer [Sparklines SparklinesLine SparklinesBars SparklinesSpots]]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

(defn ^{:category :data}
  sparkline [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]])

(defn ^{:category :data}
  sparkline-bar [data]
  [:> Sparklines data
   [:> SparklinesBars {:color "blue"}]])

(defn ^{:category :data}
  sparkline-spot [data]
  [:> Sparklines data
   [:> SparklinesLine {:color "blue"}]
   [:> SparklinesSpots {:color "blue"}]])

(register-component :p/sparkline sparkline)
(register-component :p/sparklinebar sparkline-bar)
(register-component :p/sparklinespot sparkline-spot)