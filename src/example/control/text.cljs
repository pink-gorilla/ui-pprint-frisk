(ns example.control.text
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [example.example :as example]))

(defonce state (r/atom {:name "Someone Special"
                        :super? true}))

(def panel
  [:p/panel {:title "panel with controls"}
   [:div {:class "flex items-center justify-between"}
    [:h2 "input"]
    [:p/input state :name]]

   [:h1 "state:"
    [:p (pr-str @state)]]])

(example/add
 :control/text
 panel)


