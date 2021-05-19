(ns example.app.aspectratio
  (:require
   [example.example :as example]))

(defn img []
  [:img.object-cover.object-bottom.w-full.h-full
   {:src "https://images.unsplash.com/photo-1516464731083-b50c0b838068"
    :alt "gorilla"}])

(example/add
 :app/aspect-ratio
 [:div
  ; aspect
  [:h1 "aspect ratio test"]
  [:div {:class "w-1/3 h-64 overflow-hidden"}
   [:p/aspectratio 16 9 [img]]]
  [:div.bg-gray-800.h-64
   [:p/aspectratio 16 3
    [:div.flex.justify-center.items-center.h-full
     [:p.bg-red-600 "hello"]]]]])