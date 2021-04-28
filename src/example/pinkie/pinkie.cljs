(ns example.pinkie.pinkie
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.pinkie-render :refer [pinkie-render]]
   [example.example :as example]))

(example/add
 :pinkie/renderer
 ;[:p/components]   ; simple ui in renderable-ui
 [:p/componentsui])

(defn ^{:category :pinkie
        :hidden true}
  exception-component
  "a component that throws exceptions for testing."
  []
  (throw {:type :custom-error
          :message "Something unpleasant occurred"}))

(register-component :p/exc exception-component)

(example/add
 :pinkie/bad-renderer
 [:div

  ; unknown renderer
  [:h1 "If a component cannot be found, then this will be displayed:"]
  [:p/bongo 456] ; unknown renderer

  ; exception
  [:h1 "Exception test:"]
  [:p/pinkie {:map-keywords true
              :fix-style false}
   [:p/exc]]])





