(ns pinkgorilla.ui.math
  (:require
   ;[taoensso.timbre :refer-macros (warn)]
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]]))

;; https://docs.mathjax.org/en/latest/upgrading/v2.html#v2-loading-changes
;; https://github.com/mathjax/MathJax-docs/wiki/Integrating-mathjax-into-x%3A-require.js


(defn math [data]
  [jsrender {:module "pinkie-math" :data data}])

(register-tag :math math)
