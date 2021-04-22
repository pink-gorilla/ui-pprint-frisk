(ns demo.example
  (:require
   [fipp.clojure]
   [pinkie.text :refer [text]]
   [pinkie.error :refer [error-boundary]]
   [pinkie.pinkie :refer [tag-inject]]))

(defonce examples (atom []))

(defn add [name component]
  (swap! examples conj {:name name :component component}))

(defn pr-str-nice [config]
  (let [spec (with-out-str
               (fipp.clojure/pprint config {:width 80}))]
    (println "spec: " spec)
    spec))


(defn example-page [{:keys [name component]}]
  ;[:div.flex.flex-col.w-full.h-full ; {:style {:background-color "yellow"}}
   ;[:h1.mb-5 name]
  [:div.w-full.h-screen
   [:h1 "Example: " name]
   [:div.bg-gray-300.m-5.text-blue-400
   [text (pr-str-nice component)]]
   [error-boundary
    (tag-inject component)]])