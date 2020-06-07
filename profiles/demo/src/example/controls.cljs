(ns example.controls
  (:require
   [cljs.pprint]
   [goog.object :as g]
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [tag-inject]]
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

(defn debug [v]
  (g/set js/window "x" v))


(def demo
  (let [languages ["javascript" "ruby" "clojure" "clojurescript" "ocaml" "scheme" "elixir" "c#" "R" "python"]
        state (r/atom {:name "Someone Special"
                       :language "ruby"
                       :super? true
                       :slider 3
                       :slider-to 7})]
    [:<>
     [:h3 "styling by tailwind css"]

     [:div {:class "flex items-center justify-between"}

      [:p/panel {:title "panel with controls"}
       [:h1 "wowow"]
       [:h2 "button"]
       [:p/button {:on-click #(js/alert "clicked")} "click me!"]
       [:h2 "select"]
       [:p/pselectm {:nav? true} languages state :language]
       [:h2 "checkbox"]
       [:p/checkbox state :super?]

       [:h2 "input"]
       [:p/input state :name]
       [:h2 "slider"]
       [:p/slider]]

      [:p/panel {:title "panel with controls"}
       [:h2 "ion-slider " (:slider @state)]
       [:p/slideriona {:min 1
                       :max 10
                      ;:from (:slider @state)
                     ;:on-change #((let [v  %] ; (-> % .-target .-value)]
                     ;               (debug v)
                     ;               (js/alert v)))
                       }state :slider]
       [:p/slideriona {:min 1 :max 10} state :slider :slider-to]]

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
         [:h4 "Bananas are a great potassium source!"]]]]

      [:p/panel {:title "display only controls"}
       [:h2 "Popover"]
       [:p/popover {:color "orange"
                    :placement "left"
                    :button-text "orange-l"}
        [:p/tooltip {:color "orange"
                     :title  "oranges"
                     :content "Lets make orange juice"}]]
       [:p/popover {:color "green"
                    :placement "right"
                    :button-text "trees-r"}
        [:p/tooltip {:color "green"
                     :title  "tree"
                     :content "How many trees are in a forest?"}]]

       [:h2 "progress bar"]
       [:p/progressbar 30]
       [:p/progressbar 80]

       [:p "born with grosse ohrn"]]]]))

(defn demo2 []
  (tag-inject demo))

(example/add
 "controls" ;[demo]
 [demo2])