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



; self-hosted clojurescript can not deal with clojure macros
;(def module (inline-resource "pinkgorilla/ui/vega-module.js"))


(defn vega [data]
  [jsrender {:module "pinkie-vega" :data data}])

(register-tag :vega vega)

(defn vegaa [s k]
  [vega (k @s)])

(register-tag :vegaa vegaa)