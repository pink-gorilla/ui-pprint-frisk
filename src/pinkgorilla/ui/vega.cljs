(ns pinkgorilla.ui.vega
  "plugin to render vega-charts in pink-gorilla"
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [render-js]]
   ["vega-embed" :as vega-embed]))

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


(def vega-options {:actions false
                   :defaultStyle true})

(def vega-options-js (clj->js vega-options))

(defn render-vega [dom-node data-js]
  (-> (vega-embed dom-node data-js vega-options-js)
      (.catch (fn [em]
                (println "Error in Rendering Vega Spec: "  em);
                (.appendChild dom-node
                              (.createTextNode js/document (str "Vega Spec error: " em)))))))

(defn vega [data-clj]
  [render-js {:f render-vega :data data-clj}])

(register-tag :p/vega vega)

(defn vegaa [s k]
  [vega (k @s)])

(register-tag :p/vegaa vegaa)