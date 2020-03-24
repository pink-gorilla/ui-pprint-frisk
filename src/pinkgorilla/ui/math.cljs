(ns pinkgorilla.ui.math
  (:require
   [cljs-uuid-utils.core :as uuid]
   [reagent.core :as reagent]
   ;[taoensso.timbre :refer-macros (warn)]
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]])
  (:require-macros [pinkgorilla.ui.macros :refer [inline-resource]]))


;; https://docs.mathjax.org/en/latest/upgrading/v2.html#v2-loading-changes
;; https://github.com/mathjax/MathJax-docs/wiki/Integrating-mathjax-into-x%3A-require.js


;; version 2.x rendering


#_(defn ^:export queue-mathjax-rendering ; called from js module, therefore exported
    ([mathjax id]
     (doto (.-Hub mathjax)
       (.Queue #js ["Typeset" (.-Hub mathjax) id])))
    ([id]
     (if-let [mathjax (.-MathJax js/window)]
       (queue-mathjax-rendering mathjax id)
       (warn "Missing global MathJax"))))

#_(def module "
  define([], function () {
      return {
         version: 'math 0.0.3 (mathjax v3)',
         render: function (id_or_domel, data) {
            var node = id_or_domel;
            if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
               var id  = '#'+ id_or_domel;
               node = document.querySelector (id);
               console.log ('math-module is rendering to selector id: ' + id);
            } else {
               console.log ('math-module is rendering to dom-element');
            }
            var dataJson = JSON.stringify(data)
            console.log ('math-module data: ' + dataJson);
            require(['mathjax'], function(MathJax) {
              console.log('mathjax: ' + MathJax)
              //pinkgorilla.ui.math.queue_mathjax_rendering (mathjax, selector_or_domel);
              var options = MathJax.getMetricsFor (node, true);
              MathJax.tex2chtmlPromise(data, options)
                .then((html) => {
                   node.appendChild(html);
                   var sheet = document.querySelector('#MJX-CHTML-styles');
                   if (sheet) sheet.parentNode.removeChild(sheet);
                   document.head.appendChild(MathJax.chtmlStylesheet());
                });
              }, function(err) {
                console.log('mathjax library Failed to load. error: ' + err);
            });
         }
      }
  });
")

(def module (inline-resource "pinkgorilla/ui/math-module.js"))

(defn math [data]
  [jsrender {:module module :data data}])

(register-tag :math math)
