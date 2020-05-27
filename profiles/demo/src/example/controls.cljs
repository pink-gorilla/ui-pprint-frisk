(ns example.controls
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [tag-inject renderer-list]]
   [pinkgorilla.controls.select :refer [select]]
   [demo.example :as example]))


(example/add
 "tab"
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
 "controls"
 (let [items ["javascript" "ruby" "clojure" "clojurescript" "ocaml" "scheme" "elixir" "c#" "R" "python"]
       v (r/atom "ruby")]
   [:<>
    [select items v]
    [:p "born with grosse ohrn"]]))