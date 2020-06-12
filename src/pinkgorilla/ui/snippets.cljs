(ns pinkgorilla.ui.snippets
  (:require
;   [clojure.string :as str]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.error :refer [error-boundary]]
   [pinkgorilla.ui.pinkie-render :refer [reagent-inject]]
   [pinkgorilla.ui.config :refer [link-css]]
   ["highlight.js/lib/core" :as hljs]
   ["highlight.js/lib/languages/javascript" :as javascript]
   ["highlight.js/lib/languages/markdown" :as markdown]
   ["highlight.js/lib/languages/plaintext" :as plaintext]
   ["highlight.js/lib/languages/clojure" :as clojure]
   ["highlight.js/lib/languages/clojure-repl" :as clojure-repl]))

(.registerLanguage hljs "javascript" javascript)
(.registerLanguage hljs "markdown" markdown)
(.registerLanguage hljs "plaintext" plaintext)
(.registerLanguage hljs "clojure" clojure)
(.registerLanguage hljs "clojure-repl" clojure-repl)


;(.initHighlightingOnLoad hljs)

; nicely formatted notebooks:
; https://nextjournal.com/gigasquid/parens-for-python---seaborn-visualizations
; https://www.maria.cloud/gallery?eval=true


(defn console-cell [c]
  [:div.bg-gray-100.font-mono.w-full.text-left.orange-300  ; .gray-900
   [:textarea.bg-gray-100.orange-300.w-full
    {:style {;:overflow-x "scroll"
             :overflow-x "hidden"
             :white-space "pre"}
      ;:value c     
     :defaultValue c}]])

; https://codepen.io/elomatreb/pen/hbgxp

; span {
;    display: block;
;    line-height: 1.5rem;
;    
;    &:before {
;      counter-increment: line;
;      content: counter(line);
;      display: inline-block;
;      border-right: 1px solid #ddd;
;      padding: 0 .5em;
;      margin-right: .5em;
;      color: #888
;    }

(defn code-cell [c]
  [:div.text-left.bg-gray-100 ; .border-solid
   [:pre ;.clojure
    [:code {:ref  #(when % (.highlightBlock hljs %))}
    ;.w-full.font-mono
    ;[:p
     c
    ; ]
     ]]])

(defn pinkie-cell [p]
  [:div.w-full.mt-5.bg-gray-100
   [error-boundary
    [reagent-inject {:map-keywords true} p]]])

(defn snippet [{:keys [code pinkie out]}]
  [:div.w-full.mt-5.p-2
   (when code
     [code-cell code])
   (when out
     [console-cell out])
   (when pinkie
     [pinkie-cell pinkie])])

;(def theme "default.css")
;(def theme "zenburn")
(def theme "github")

(defn ^{:category :pinkie}
  snippets
  "renders a snippet list"
  [list]
  [:div.w-full.h-full.bg-gray-100
   [link-css (str "highlight.js/styles/" theme ".css")]
   ;[:link {:rel "stylesheet" :href (str "/highlight.js/styles/" theme ".css")}]
   (into [:div] (map snippet list))])

(register-component :p/snippets snippets)