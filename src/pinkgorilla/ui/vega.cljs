(ns pinkgorilla.ui.vega
  "plugin to render vega-charts in pink-gorilla"
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]]))

;; The follwing RequireJS modules are defined at jsrender initialization
;; 'vega-embed'
;; 'vega-lib'
;; 'vega-lite'
;; 'vega'

;; NOTES:
;; Vega-Embed depends on Vega-Lite and Vega
;; Vega-Lite depends on Vega
;; The load order has to be VEGA, VEGA-LITE, VEGA-EMBED.

;; https://github.com/vega/vega-embed/issues/8
;; https://github.com/biocore/qurro/commit/baf8542bd08dfdb5a078bca3f73cddbd79faef93

; for vega embedding options see:  https://github.com/vega/vega-embed


(defn vega [data]
  [jsrender {:module "pinkie-vega" :data data}])

(register-tag :vega vega)

(defn vegaa [s k]
  [vega (k @s)])

(register-tag :vegaa vegaa)