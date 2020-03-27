(ns pinkgorilla.ui.math
  (:require
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


; self-hosted clojurescript can not deal with clojure macros
;(def module (inline-resource "pinkgorilla/ui/math-module.js"))

(defn math [data]
  [jsrender {:module "pinkie-math" :data data}])

(register-tag :math math)
