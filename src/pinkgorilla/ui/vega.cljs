(ns pinkgorilla.ui.vega
  "plugin to render vega-charts in pink-gorilla"
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]])
  (:require-macros [pinkgorilla.ui.macros :refer [inline-resource]]))

;; UI Modules use RequireJS.
;; Require-JS configuration has to be done centrally.

;; The follwing RequireJS modules are defined at jsrender initialization
;; 'vega-embed'
;; 'vega-lib'
;; 'vega-lite'
;; 'vega'

;; "https://vega.github.io/schema/vega/v5.json


;; NOTES:
;; Vega-Embed depends on Vega-Lite and Vega
;; Vega-Lite depends on Vega
;; The load order has to be VEGA, VEGA-LITE, VEGA-EMBED.

;; https://github.com/vega/vega-embed/issues/8
;; https://github.com/biocore/qurro/commit/baf8542bd08dfdb5a078bca3f73cddbd79faef93


; for vega embedding options see:  https://github.com/vega/vega-embed


#_(def module "
  define([], function () {
      return {
         version: 'vega 0.0.4',
         render: function (id_or_domel, data) {
            var selector_or_domel = id_or_domel;
            if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
               selector_or_domel = '#'+ id_or_domel;
               console.log ('vega-module is rendering to selector id: ' + selector_or_domel);
            } else {
               console.log ('vega-module is rendering to dom-element');
            }
            var dataJson = JSON.stringify(data)
            console.log ('vega-module data: ' + dataJson);
            var vegaOptions = {
               actions: false, 
               defaultStyle: true
            };
            require(['vega', 'vega-lite', 'vega-embed'], function(vega, vegaLite, vegaEmbed) {
              vegaEmbed(selector_or_domel, data, vegaOptions).catch(function(em) {
                  console.log('Error in Rendering Vega Spec: ' + em);
                  var txt = document.createTextNode ('Vega Spec error' + em);
                  selector_or_domel.appendChild (txt);
                 });
              }, function(err) {
                console.log('Vega-Embed failed to load');
                var txt = document.createTextNode ('Vega-Embed failed to load');
                selector_or_domel.appendChild (txt);
            });
         }
      }
  });
")

(def module (inline-resource "pinkgorilla/ui/vega-module.js"))

(defn vega [data]
  [jsrender {:module module :data data}])

(register-tag :vega vega)

(defn vegaa [s k]
  [vega (k @s)])

(register-tag :vegaa vegaa)