(ns pinkgorilla.ui.pinkie.clock
  (:require
   [reagent.core :as r]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.html :refer [html]]))

(def clock-style
  "<style> 

/* Binary clock */

.clock-main {
    background: #333;
    color: #cdcdcd;
    padding-top: 55px;
    padding-left: 20px;
    float: left;
    font-size: 28px;
    line-height: 34px;
    width: 620px;
    cursor: pointer;
}
.clock-main.wide {
    width: 790px;
}
.clock-cell {
    width: 55px;
    height: 55px;
    text-align: center;
    margin: 0 20px 20px 0;
}
.clock-cell.dark {
    background-color: #454545;
}
.clock-cell.light {
    background-color: #eee;
}
.clock-col {
    margin: 0;
    float: left;
}
.clock-legend > .clock-cell {
    margin-top: 10px;
}
.clock-pair {
    margin: 0;
    float: left;
}
.clock-pair:not(:last-child) {
    margin-right: 20px;
    /* border-right: 1px solid #454545; */
}
 </style>
")

(defn cell [n bit]
  [:div.clock-cell {:class (if (bit-test n bit)
                             "light"
                             "dark")}])

(defn column [n]
  [:div.clock-col
   [cell n 3]
   [cell n 2]
   [cell n 1]
   [cell n 0]
   [:div.clock-cell n]])

(defn column-pair [n]
  [:div.clock-pair
   [column (quot n 10)]
   [column (mod n 10)]])

(defn legend [& items]
  (into [:div.clock-col.clock-legend]
        (map (partial vector :div.clock-cell)
             items)))

(defn ^{:category :demo}
  clock [date show-100s toggle-100s]
  [:div.clock-main {:on-click toggle-100s
                    :class (when show-100s "wide")}
   [html clock-style]
   [legend 8 4 2 1]
   [column-pair (.getHours date)]
   [column-pair (.getMinutes date)]
   [column-pair (.getSeconds date)]
   (when show-100s
     [column-pair (-> (.getMilliseconds date)
                      (quot 10))])])

(def clock-state (r/atom {:time (js/Date.)
                          :show-100s false}))

(defn update-time []
  (swap! clock-state assoc :time (js/Date.)))

(defn ^{:category :demo}
  binary-clock
  "displays a binary clock; on click with seconds.
  useful to debug reagent component that do not update"
  []
  (let [{:keys [time show-100s]} @clock-state]
    (if show-100s
      (r/next-tick update-time)
      (js/setTimeout update-time 1000))
    [clock time show-100s
     #(swap! clock-state update-in [:show-100s] not)]))

(register-component :p/clock binary-clock)


