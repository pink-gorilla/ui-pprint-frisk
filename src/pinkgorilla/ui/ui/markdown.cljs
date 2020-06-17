(ns pinkgorilla.ui.ui.markdown
  "markdown rederers are available as js library or react library
   we implemented a markdown viewer via a js library marked
   because the react component did not work.
   "
  (:require
   ["marked" :as marked]
   [pinkie.pinkie :refer-macros [register-component]]))

(defn ^{:category :ui}
  markdown
  "reagent markdown render component
   usage:
    [markdown markdown-string]"
  [md]
  (if (nil? md) ; marked will crash on (nil? md), so we catch nil. 
    [:p "Empty Markdown"]
    [:div.gorilla-markdown
     {:dangerouslySetInnerHTML
      {:__html (marked md)}}]))

;; awb99:
;; 
;; I tried two react components:
;; 
;; 1. terra-markdown
;;    ["terra-markdown" :as react-md]
;;    
;; 2. react-markdown
      ;["react-markdown" :as react-md]
   ; react-markdown requires npm dependency: 
   ; unist-util-visit ^1.3.0
   ; unist-util-visit-parents@^2.0.0

#_(defn markdown [md]
    [:div.gorilla-markdown
     [:> react-md {:src md :id "bongo"}]])

#_(println "react-md: " (pr-str react-md))

(register-component :p/markdown markdown)