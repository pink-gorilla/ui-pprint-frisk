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


(def img
  [:img.object-cover.object-bottom.w-full.h-full
   {:src "https://images.unsplash.com/photo-1516464731083-b50c0b838068"
    :alt "gorilla"}])

(example/add
 "bad-renderer"
 [:div
  [:span 123]
  [:p/bongo 456] ; unknown renderer
  [:h1 "aspect ratio test"]
  [:div {:class "w-1/3 h-64 overflow-hidden"}
   [:p/aspectratio 16 9 img]]
  [:div.bg-gray-800.h-64
   [:p/aspectratio 16 3
    [:div.flex.justify-center.items-center.h-full
     [:p.bg-red-600 "hello"]]]]])


(example/add
 "html"
 [:div
  [:h1 "html in reagent"]
  [:p "please open developer tools to check if jquery gets loaded below."]
  [:p/phtml "<script src='https://code.jquery.com/jquery-3.4.1.min.js'>>/script>"]])



