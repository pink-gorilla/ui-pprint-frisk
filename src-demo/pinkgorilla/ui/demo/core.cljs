(ns pinkgorilla.ui.demo.core
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [tag-inject renderer-list]]
   ; add dependencies of this project to bundle
   [pinkgorilla.ui.default-renderer]

   ; DEMOS
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
   [pinkgorilla.ui.demo.clock]))

(defn print-registered-tags []
   (with-out-str
     (cljs.pprint/print-table (renderer-list))))

(defn examples
  "examples wraps an array of examples and puts a box around all examples"
  [items]
  [:div#examples
   (into [:div#examplejson]
         (doall (map-indexed (fn [index item]
                               [:div {:style {:background-color "yellow"}}
                                [:p "example " index]
                                item])
                             items)))])

(def app
  [:<>
  
   ;[:zzz]
   (examples ; needs to be function because tag-inject cannot deal with functions
    [
      [:text (print-registered-tags)]
     pinkgorilla.ui.demo.gtable/demo
    ; js based renderer
     pinkgorilla.ui.demo.math/demo
     pinkgorilla.ui.demo.vega/demo
     pinkgorilla.ui.demo.json/demo
     pinkgorilla.ui.demo.highchart/demo
     ;reagent based renderer
     pinkgorilla.ui.demo.leaflet/demo
     pinkgorilla.ui.demo.sparklines/demo
     pinkgorilla.ui.demo.player/demo
     pinkgorilla.ui.demo.aggrid/demo
     pinkgorilla.ui.demo.clock/demo])])


(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  (js/console.log (print-registered-tags))
  (r/render (tag-inject app)
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
