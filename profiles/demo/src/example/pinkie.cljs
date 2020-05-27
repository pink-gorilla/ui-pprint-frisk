(ns example.pinkie
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [tag-inject renderer-list]]
   [pinkgorilla.controls.select :refer [select]]
   [demo.example :as example]))

(defn print-registered-tags []
  (with-out-str
    (cljs.pprint/print-table (renderer-list))))

(example/add
 "renderer"
 [:p/text (print-registered-tags)])

(example/add
 "bad-renderer"
 [:div
  [:span 123]
  [:p/bongo 456]
  [:span 789]])


(example/add
 "html"
 [:div
  [:h1 "html in reagent"]
  [:p "please open developer tools to check if jquery gets loaded below."]
  [:p/phtml "<script src='https://code.jquery.com/jquery-3.4.1.min.js'>>/script>"]])



