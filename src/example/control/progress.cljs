(ns example.control.progress
  (:require
   [example.example :as example]))

(def panel
  [:p/panel {:title "panel with controls"}
   [:h2 "progress bar"]
   [:p/progressbar 30]
   [:p/progressbar 80]

   [:p "born with grosse ohrn"]])

(example/add
 :control/progress
 panel)


