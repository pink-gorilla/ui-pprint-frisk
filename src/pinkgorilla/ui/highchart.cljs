(ns pinkgorilla.ui.highchart
  "reagent component to render highchart-spec via highcharts.js
   Usage:  [:highchart spec-as-clj-data]

   Highchart renderer is a pure javascript renderer, the conversion
   of the spec as clj-data to jaascript is doen in the jsrender component
   "
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]]))

;; https://api.highcharts.com/class-reference/Highcharts.Chart

;; highcharts.Chart (selector_or_dom_node, data, options, callback)


(defn highchart [data]
  [jsrender {:module "pinkie-highchart" :data data}])

(register-tag :highchart highchart)

(comment

  (ns pinkgorilla.ui.pinkie))
