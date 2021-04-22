(ns example.pinkie
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.pinkie-render :refer [pinkie-render]]
   [example.example :as example]))

(example/add
 :gorilla/renderer
 ;[:p/components]   ; simple ui in renderable-ui
 [:p/componentsui])

(defn img []
  [:img.object-cover.object-bottom.w-full.h-full
   {:src "https://images.unsplash.com/photo-1516464731083-b50c0b838068"
    :alt "gorilla"}])

(defn ^{:category :pinkie
        :hidden true}
  exception-component
  "a component that throws exceptions for testing."
  []
  (throw {:type :custom-error
          :message "Something unpleasant occurred"}))

(register-component :p/exc exception-component)

(example/add
 :gorilla/bad-renderer
 [:div

  ; unknown renderer
  [:h1 "If a component cannot be found, then this will be displayed:"]
  [:p/bongo 456] ; unknown renderer

  ; exception
  [:h1 "Exception test:"]
  [:p/pinkie {:map-keywords true
              :fix-style false}
   [:p/exc]]])

(example/add
 :gorilla/aspect-ratio
 [:div
  ; aspect
  [:h1 "aspect ratio test"]
  [:div {:class "w-1/3 h-64 overflow-hidden"}
   [:p/aspectratio 16 9 [img]]]
  [:div.bg-gray-800.h-64
   [:p/aspectratio 16 3
    [:div.flex.justify-center.items-center.h-full
     [:p.bg-red-600 "hello"]]]]])

(example/add
 :gorilla/html
 [:div
  [:h1 "html in reagent"]
  [:p "please open developer tools to check if ramda gets loaded below."]
  [:p/phtml "<script src='https://cdn.jsdelivr.net/npm/ramda@0.27.0/dist/ramda.min.js'>>/script>"]])





