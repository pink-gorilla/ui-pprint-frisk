(ns pinkgorilla.ui.highchart
  "reagent component to render highchart-spec via highcharts.js
   Usage:  [:highchart spec-as-clj-data]

   Highchart renderer is a pure javascript renderer, the conversion
   of the spec as clj-data to jaascript is doen in the jsrender component
   "
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]])
  (:require-macros [pinkgorilla.ui.macros :refer [inline-resource]]))

;; UI Modules use RequireJS.
;; Require-JS configuration has to be done centrally.
;; The follwing RequireJS modules are defined in PinkGorilla Notebook
;; highcharts

;; https://api.highcharts.com/class-reference/Highcharts.Chart

;; highcharts.Chart (selector_or_dom_node, data, options, callback)

#_(def module "
  define([], function () {
      return {
         version: 'highcharts 0.0.3',
         render: function (id_or_domel, data) {
            var selector_or_domel = id_or_domel;
            if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
               selector_or_domel = '#'+ id_or_domel;
               console.log ('highcharts-module is rendering to selector id: ' + selector_or_domel);
            } else {
               console.log ('highcharts-module is rendering to dom-element');
            }
            var dataJson = JSON.stringify(data)
            console.log ('highcharts-module data: ' + dataJson);
            require(['highcharts'], function(highcharts) {
              var chart = new highcharts.Chart (selector_or_domel, data) ;.catch(console.warn);
              }, function(err) {
                console.log('highcharts Failed to load!');
            });
         }
      }
  });
")

(def module (inline-resource "pinkgorilla/ui/highchart-module.js"))

(defn highchart [data]
  [jsrender {:module module :data data}])

(register-tag :highchart highchart)

(comment

  (ns pinkgorilla.ui.pinkie))
