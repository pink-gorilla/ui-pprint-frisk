(ns pinkgorilla.ui.math
  (:require
   ;[taoensso.timbre :refer-macros (warn)]
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [render-js]]
   ["/pinkgorilla/ui/mathjax" :as mathjax2]
   #_["mathjax" :as mathjax]))

(def mathjax (.-MathJax js/window))

(println "mathjax2: " mathjax2)

;; https://docs.mathjax.org/en/latest/upgrading/v2.html#v2-loading-changes
;; https://github.com/mathjax/MathJax-docs/wiki/Integrating-mathjax-into-x%3A-require.js

;Note the `delayStartupUntil=configured` parameter */
;         // 'mathjax' : 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS-MML_SVG-full.js&delayStartupUntil=configured'
;         //  'mathjax' : 'https://cdnjs.cloudflare.com/ajax/libs/mathjax/3.0.1/es5/sre/sre_browser.min.js?config=TeX-AMS_HTML&amp;delayStartupUntil=configured' // .js extension is added automatically
;           'mathjax' : 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml' // .js extension is added automatically
;         //"mathjax" : "js/mathjax"

(def default-config
  {:messageStyle "none"
   :showProcessingMessages false
   :skipStartupTypeset     true
   :tex2jax                {:inlineMath [["@@" "@@"]]}})

;{tex: {inlineMath: [['$', '$'], ['\\(', '\\)']]},
;                //                    svg: {fontCache: 'global'} 
;                //                   }


(def config2 {:loader {:load ["input/tex" "output/svg"]}})

(defn init-mathjax! [config]
  (println "MyMathJax init :" config);
  (.init js/Mathjax (clj->js config2)))


;var options = MathJax.getMetricsFor (node, true);
 ;;           MathJax.tex2chtmlPromise(data, options)
   ;           .then((html) => {
    ;             node.appendChild(html);
     ;            var sheet = document.querySelector('#MJX-CHTML-styles');
      ;           if (sheet) sheet.parentNode.removeChild(sheet);
       ;          document.head.appendChild(MathJax.chtmlStylesheet());
        ;      })


(defn add-math-css []
  (let [sheet (.querySelector js/document "#MJX-CHTML-styles")]
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
;.then((node) => {
           ;     const adaptor = MathJax.startup.adaptor;
 ; console.log(adaptor.outerHTML(node));


(defn render-math [dom-node data-js]
  (let [;options (clj->js options)
        options (.getMetricsFor mathjax dom-node true)]
    (-> (.tex2chtmlPromise mathjax data-js options) ;  .mathml2svgPromise
        (.then (fn [math-node]
                 (.appendChild dom-node math-node)
                 (add-math-css))))))

(defn math [data-clj]
  [render-js {:f render-math :data data-clj}])

(register-tag :p/math math)

(init-mathjax! default-config)


