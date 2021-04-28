(ns example.control.slider
  (:require
   [reagent.core :as r]
   [example.example :as example]))

(defonce state (r/atom {:slider 3
                        :slider-to 7}))

(def panel-slider
  [:p/panel {:title "panel with controls"}
   [:h2 "ion-slider " (:slider @state)]
   [:p/slideriona {:min 1
                   :max 10
                      ;:from (:slider @state)
                     ;:on-change #((let [v  %] ; (-> % .-target .-value)]
                     ;               (debug v)
                     ;               (js/alert v)))
                   }state :slider]
   [:p/slideriona {:min 1 :max 10} state :slider :slider-to]])

(example/add
 :control/slider
 panel-slider)

(def panel-slider2
  [:div
   [:h2 "slider"]
   [:p/slider]])

(example/add
 :control/slider2
 panel-slider2)