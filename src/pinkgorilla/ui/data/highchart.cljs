(ns pinkgorilla.ui.data.highchart
  "Highchart renderer is a pure javascript renderer, the conversion
   of the spec as clj-data to javascript is done in the render-js component
   "
  (:require
   [clojure.set :refer [rename-keys]]
   ["highcharts" :as highcharts]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.jsrender :refer [render-js]]
   [pinkgorilla.ui.box :refer [box]]))

;; https://www.highcharts.com/
;; https://github.com/highcharts/highcharts
;; https://api.highcharts.com/class-reference/Highcharts.Chart

(defn render-highchart [dom-node data]
  (highcharts/Chart. dom-node data); //.catch(console.warn);
  )

(defn ^{:category :data}
  highchart
  "reagent component to render highchart-spec via highcharts.js
   Usage:  [:p/highchart spec-as-clj-data]"
  [data]
  [render-js {:f render-highchart :data data}])

(defn highchart-box [data box]
  (let [s (-> (:style box)
              (select-keys  [:width-px :height-px])
              (rename-keys {:width-px :width
                            :height-px :height}))
        b (assoc data :chart s)
        ;b (assoc data :chart  {:width 300 :height 300})
        ]
    (println "highchart boxed: " b)
    b))

(defn ^{:category :data}
  highchart-boxed
  "reagent component to render highchart-spec via highcharts.js
   Usage:  [:p/highchart spec-as-clj-data]"
  [data]
  [box {:s :small
        :render highchart
        :box highchart-box
        :data data}])

(register-component :p/highchart highchart-boxed)

