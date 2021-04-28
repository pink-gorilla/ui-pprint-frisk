(ns example.layout.tab
  (:require
   [example.example :as example]))

(example/add
 :layout/tab
 [:div
  [:ul {:class "list-reset flex border-b"}
   [:li {:class "-mb-px mr-1"}
    [:a {:class "bg-white inline-block border-l border-t border-r rounded-t py-2 px-4 text-blue-dark font-semibold", :href "#"} "Active"]]
   [:li {:class "mr-1"}
    [:a {:class "bg-white inline-block py-2 px-4 text-blue hover:text-blue-darker font-semibold", :href "#"} "Tab"]]
   [:li {:class "mr-1"}
    [:a {:class "bg-white inline-block py-2 px-4 text-blue hover:text-blue-darker font-semibold", :href "#"} "Tab"]]
   [:li {:class "mr-1"}
    [:a {:class "bg-white inline-block py-2 px-4 text-grey-light font-semibold", :href "#"} "Tab"]]]])

(example/add
 :layout/tab
 [:div
  [:h2 "Tabs"]
  [:p/tabs
   [:p/tab {:title "a"
            :isActive false
            :color "red"
            :tabIndex 1}
    [:h4 "We love the A-team !"]]
   [:p/tab {:title "b"
            :isActive true
            :color "green"
            :tabIndex 0}
    [:h4 "Bananas are a great potassium source!"]]]])