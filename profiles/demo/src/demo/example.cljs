(ns demo.example
  (:require
   [pinkgorilla.ui.error :refer [error-boundary]]
   [pinkgorilla.ui.pinkie :refer [tag-inject]]))

(defonce examples (atom []))

(defn add [name component]
  (swap! examples conj {:name name :component component}))

(defn show-example [{:keys [name component]}]
  [:div ; {:style {:background-color "yellow"}}
   [:h1.mb-5 name]
   [error-boundary
    (tag-inject component)
    ]])