(ns pinkgorilla.ui.demo.core
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [reagent.dom]
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

(defn example [component tagline]
  [:div {:style {:background-color "yellow"}}
   [:p "example: " [:b tagline] ]
   component])


(def app
  [:<>
   [:p/text (print-registered-tags)]
   [example pinkgorilla.ui.demo.gtable/demo "gtable"]
   [example [:div [:span 123] [:p/bongo 456] [:span 789]] "bad-renderer"]
   [example [:div [:h1 "jquery gets loaded below .. jippie "]
                  [:p/phtml "<script src='https://code.jquery.com/jquery-3.4.1.min.js'>>/script>"]]]
   
     ; js based renderer
   [example pinkgorilla.ui.demo.math/demo "js-mathjax"]
   [example pinkgorilla.ui.demo.vega/demo "js-vega"]
   [example pinkgorilla.ui.demo.json/demo "js-json"]
   [example pinkgorilla.ui.demo.highchart/demo "js-highchart"]
     ;reagent based renderer
   [example pinkgorilla.ui.demo.leaflet/demo "leaflet-map"]
   [example pinkgorilla.ui.demo.sparklines/demo "sparklines"]
   [example pinkgorilla.ui.demo.player/demo "player"]
   [example pinkgorilla.ui.demo.aggrid/demo "ag-grid" ]
   [example pinkgorilla.ui.demo.clock/demo "clock"]
   ])


(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  ;(js/console.log (print-registered-tags))
  (reagent.dom/render (tag-inject app)
            (.getElementById js/document "app")))

(defn ^:export init []
  (start))
