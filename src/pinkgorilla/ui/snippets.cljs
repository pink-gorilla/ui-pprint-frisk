(ns pinkgorilla.ui.snippets
  (:require
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.error :refer [error-boundary]]
   [pinkgorilla.ui.pinkie-render :refer [reagent-inject]]
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

(println "CLOJURE: " clojure)
;(.initHighlightingOnLoad hljs)


(defn console-cell [c]
  [:div.bg-gray-700.gray-100.font-mono.w-full
   [:textarea.bg-gray-700 {:defaultValue c
                           ;:value c
                           }]])

(defn code-cell [c]
  [:pre ;.clojure
   [:code {:ref  #(.highlightBlock hljs %)}
    ;.w-full.bg-blue-200.font-mono
    ;[:p
    c
    ; ]
    ]])

(defn pinkie-cell [p]
  [:div.w-full.mt-5
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

(defn ^{:category :pinkie}
  snippets
  "renders a snippet list"
  [list]
  [:div.w-full.h-full
   [:link {:rel "stylesheet" :href "/highlight.js/styles/default.css"}]
   (into [:div] (map snippet list))])

(register-component :p/snippets snippets)