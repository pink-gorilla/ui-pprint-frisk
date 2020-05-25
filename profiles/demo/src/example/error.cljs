(ns example.error
  (:require
   [cljs.pprint]
   [pinkgorilla.ui.pinkie :refer [tag-inject renderer-list]]
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
  [:h1 "jquery gets loaded below .. jippie "]
  [:p/phtml "<script src='https://code.jquery.com/jquery-3.4.1.min.js'>>/script>"]])

