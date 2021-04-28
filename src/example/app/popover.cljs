(ns example.app.popover
  (:require
   [example.example :as example]))

(def panel-popping
  [:p/panel {:title "display only controls"}
   [:h2 "Popover"]
   [:p/popover {:color "yellow"
                :placement "left"
                :button-text "yellow-l"}
    [:p/tooltip {:color "red"
                 :title  "oranges"
                 :content "Lets make orange juice"}]]
   [:p/popover {:color "green"
                :placement "right"
                :button-text "trees-r"}
    [:p/tooltip {:color "green"
                 :title  "tree"
                 :content "How many trees are in a forest?"}]]])

(example/add
 :app/popover
 panel-popping)