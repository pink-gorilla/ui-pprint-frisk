(ns pinkgorilla.ui.ui.math
  " MathJax has v2 and v3. v3 is breaking the v2 api.
    es6 modules for the browser not working; this means browser 
    will load mathjax to window/MathJax
    so we ship the compiled mathjax.js bundle
   "
  (:require
   ;[taoensso.timbre :refer-macros (warn)]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.jsrender :refer [render-js]]
   ["/pinkgorilla/ui/ui/mathinit" :as mathjax-init]))

#_(defn add-math-css []
    (let [mathjax (.-MathJax js/window)
          sheet (.querySelector js/document "#MJX-CHTML-styles")]
      (when (not sheet)
        (.appendChild (.-head js/document) (.chtmlStylesheet mathjax)))))

(def options
  {:display true ;"process as inline math"
   :em 16 ; em-size in pixels
   :ex 8 ; ex-size in pixels
   :containerWidth (* 8 16); 'width of container in pixels'
   :css false ; output the required CSS rather than the HTML itself
   :fontCache true ; 'whether to use a local font cache or not'
   :dist true ; 'true to use webpacked version, false to use MathJax source files'
   })

(defn render-math [dom-node data-js]
  (let [mathjax (.-MathJax js/window)
        ;options (clj->js options)
        options (.getMetricsFor mathjax dom-node true)]
    (-> (.tex2svgPromise mathjax data-js options) ;
        (.then (fn [math-node]
                 (.appendChild dom-node math-node)
                 #_(add-math-css))))))

(defn ^{:category :ui}
  math
  "displays mathematical formulas"
  [data-clj]
  [render-js {:f render-math :data data-clj}])

(register-component :p/math math)



