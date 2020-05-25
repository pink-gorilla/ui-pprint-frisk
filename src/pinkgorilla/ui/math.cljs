(ns pinkgorilla.ui.math
  " MathJax has v2 and v3. v3 is breaking the v2 api.
    es6 modules for the browser not working; this means browser 
    will load mathjax to window/MathJax
    so we ship the compiled mathjax.js bundle
   "
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [render-js]]
   ["/pinkgorilla/ui/mathjax" :as mathjax-loader])) ;  this creates window/MathJax

; print it, so shadow does not remove the dependency
; is this necessary ?
(println "mathjax-loader: " mathjax-loader)

(def mathjax (.-MathJax js/window))


#_(def default-config
    {:messageStyle "none"
     :showProcessingMessages false
     :skipStartupTypeset     true
     :tex2jax                {:inlineMath [["@@" "@@"]]}})

(def default-config
  {:loader {:load ["input/tex" "output/svg"]}})

(defn add-css 
  "add mathjax css from the mathjax js bundle to the dom
   can be called multiple times.
   "
  []
  (let [sheet (.querySelector js/document "#MJX-CHTML-styles")]
    (when (not sheet)
      (println "mathjax adding css.")
      (.appendChild (.-head js/document) (.chtmlStylesheet mathjax)))))

(defn init-mathjax! [config]
  (let [mathjax (.-MathJax js/window)]
    (if (nil? mathjax)
      (println "mathjax-init: error loading Mathjax to js/Window")
      (do
        (println "mathjax version:" (.-version mathjax))
        ;(println "mathjax-init config: " config)
        (add-css)
        #_(-> (.init mathjax (clj->js config))
          ;(.then (fn [MathJax]
                   ;(let [svg (.tex2svg MathJax "\\frac{1}{x^2-1}" #js {:display true})]
                    ; (println "svg: " svg)
      ;console.log(MathJax.startup.adaptor.outerHTML(svg));          
           ;        ))
              #_(.catch (fn [err]
                          (println "mathjax-init error: " (.message err)))))))))


;; https://github.com/mathjax/MathJax-docs/wiki/Integrating-mathjax-into-x%3A-require.js

;Note the `delayStartupUntil=configured` parameter */
;         // 'mathjax' : 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS-MML_SVG-full.js&delayStartupUntil=configured'
;         //  'mathjax' : 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/3.0.1/es5/sre/sre_browser.min.js?config=TeX-AMS_HTML&amp;delayStartupUntil=configured' // .js extension is added automatically
;           'mathjax' : 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml' // .js extension is added automatically
;         //"mathjax" : "js/mathjax"



(def options
  {:display true ;"process as inline math"
   :em 16 ; em-size in pixels
   :ex 8 ; ex-size in pixels
   :containerWidth (* 8 16); 'width of container in pixels'
   :css false ; output the required CSS rather than the HTML itself
   :fontCache true ; 'whether to use a local font cache or not'
   :dist true ; 'true to use webpacked version, false to use MathJax source files'
   })


; our mathjax.js bundle does not contain the svg part
#_(defn mathml->svg [ml]
    (-> (.mathml2svgPromise mathjax ml options)
        (.then (fn [math-node]
                 (println "svg:")))))


(defn render-math [dom-node data-js]
  (let [;options (clj->js options)
        options (.getMetricsFor mathjax dom-node true)]
    (-> (.tex2chtmlPromise mathjax data-js options)
        (.then (fn [math-node]
                 (add-css)
                 (.appendChild dom-node math-node))))))

(defn math [data-clj]
  [render-js {:f render-math :data data-clj}])

(register-tag :p/math math)

;(init-mathjax! default-config)






