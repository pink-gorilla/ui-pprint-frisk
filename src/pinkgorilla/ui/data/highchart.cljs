(ns pinkgorilla.ui.data.highchart
  "Highchart renderer is a pure javascript renderer, the conversion
   of the spec as clj-data to javascript is done in the render-js component
   "
  (:require
   ["highcharts" :as highcharts]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.jsrender :refer [render-js]]))

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

(register-component :p/highchart highchart)

