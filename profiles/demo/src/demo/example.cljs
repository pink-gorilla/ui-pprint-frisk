(ns demo.example
  (:require
   [pinkie.error :refer [error-boundary]]
   [pinkie.pinkie :refer [tag-inject]]))

(defonce examples (atom []))

(defn add [name component]
  (swap! examples conj {:name name :component component}))

(defn show-example [{:keys [name component]}]
  [:div.flex.flex-col.w-full.h-full ; {:style {:background-color "yellow"}}
   [:h1.mb-5 name]
   [:div.w-full.h-full
    [error-boundary
     (tag-inject component)]]])