(ns pinkgorilla.ui.control.ionslider
  (:require
   [reagent.core :as r]
   ["jquery" :as jq] ; jquery is a dependency, but it needs to be required separately.
   ["react-ion-slider" :as IonRangeSlider]
   [pinkie.pinkie :refer-macros [register-component]]))

; shiny: http://ionden.com/a/plugins/ion.rangeSlider/demo_interactions.html
; https://www.npmjs.com/package/react-ion-slider#1-before-using-react-ion-slider-please-import-module

; type= {} min= {} max= {} from= {} to= {} step= {} values= {} keyboard= {}

;(println "slider..ion..")

(def default-options
  {:type "single"})

(defn ^{:category :control}
  slider-ion [options]
  (let [on-change (:on-change options)
        on-change-wrapped (fn [v] (when on-change (on-change (js->clj v))))
        options (merge default-options options {:on-change on-change-wrapped})]
 ; [:h1 "ion slider"]
    [:> IonRangeSlider options]
    #_(into [:> IonRangeSlider options]
            (r/children (r/current-component)))))

(defn ^{:category :control}
  slider-ion-a
  ([options state kw-from]
   (slider-ion-a options state kw-from nil))
  ([options state kw-from kw-to]
   (let [on-change (fn [v]
                     (let [from (get v "from")
                           to (get v "to")]
                       (println "slider value: from: " from "to: " to)
                       (swap! state assoc kw-from from)
                       (when kw-to
                         (swap! state assoc kw-to to))))
         options-to (if kw-to {:to (kw-to @state)
                               :type "double"} {})
         options (merge options
                        {:on-change on-change
                         :from (kw-from @state)}
                        options-to)]
     [slider-ion options])))

;(println "slider..ion.. registering..")
(register-component :p/sliderion slider-ion)
(register-component :p/slideriona slider-ion-a)

;(println "slider..ion.. registering done.")

