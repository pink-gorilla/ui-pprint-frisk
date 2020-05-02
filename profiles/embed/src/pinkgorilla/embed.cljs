(ns pinkgorilla.embed
  (:require
   [goog.object :as g]
   [reagent.dom]
   [reagent.core]

   ; add ui renderer dependencies  to bundle
   [pinkgorilla.ui.default-renderer]
   ; from renderable-ui
   [pinkgorilla.ui.demo.gtable]
   ; js based enderer
   [pinkgorilla.ui.demo.highchart]
   [pinkgorilla.ui.demo.json]
   [pinkgorilla.ui.demo.vega]
   [pinkgorilla.ui.demo.math]
   ; reagent based renderer
   [pinkgorilla.ui.demo.leaflet]
   [pinkgorilla.ui.demo.sparklines]
   [pinkgorilla.ui.demo.player]
   [pinkgorilla.ui.demo.aggrid]
   [pinkgorilla.ui.demo.clock]

   [clojure.edn :as edn]
   [pinkgorilla.pinkie :refer [pinkie]]))


(defn pinkie-render [data]
  [:p (pr-str data)]
  [pinkie {:map-keywords true
           :widget false} data]
  )

(defn render [node]
  (reagent.dom/render
   [pinkie-render (edn/read-string (.-innerHTML node))]
   node))

(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (let [els (.getElementsByClassName js/document "pinkie")
        l (.-length els)
        list (map #(g/get els %) (range l))]
    (println "elements: " l ": " list)
    ;(reagent.dom/render pinkie-render (first list))
    ;(reagent.dom/render pinkie-render (second list))
    (doall (map render list))))

(defn ^:export init []
  (start))