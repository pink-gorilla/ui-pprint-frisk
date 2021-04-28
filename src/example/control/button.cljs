(ns example.control.button
  (:require
   [example.example :as example]))

(def panel
  [:p/panel {:title "panel with controls"}
   [:div {:class "flex items-center justify-between"}
    [:p/button {:on-click #(js/alert "clicked")} "click me!"]]])

(example/add
 :control/button
 panel)


