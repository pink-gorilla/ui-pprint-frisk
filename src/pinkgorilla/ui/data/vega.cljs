(ns pinkgorilla.ui.data.vega
  "plugin to render vega-charts in pink-gorilla"
  (:require
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkie.jsrender :refer [render-js]]
   ["vega-embed" :as vega-embed]))

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

(defn ^{:category :data}
  vega
  "displays chart defined in vega spec
   "
  [data-clj]
  [render-js {:f render-vega :data data-clj}])

(register-component :p/vega vega)

(defn ^{:category :data}
  vegaa
  "displays chart defined in vega spec
   gets spec from the key k in atom s 
   "
  [s k]
  [vega (k @s)])

(register-component :p/vegaa vegaa)