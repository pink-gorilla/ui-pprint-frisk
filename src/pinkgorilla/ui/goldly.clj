(ns pinkgorilla.ui.goldly
  (:require
   [goldly.sci.bindings :refer [add-cljs-namespace add-cljs-bindings]]
   [systems.snippet-registry :refer [add-snippet]]))

(add-cljs-namespace [pinkgorilla.ui.goldly])


; gorilla-ui


(add-snippet {:type :goldly-clj
              :category :gorilla-ui
              :id :highcharts
              :filename "snippets/gorilla-ui/highcharts.clj"})

(add-snippet {:type :goldly-clj
              :category :gorilla-ui
              :id :sparkline
              :filename "snippets/gorilla-ui/sparkline.clj"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :video
              :filename "snippets/gorilla-ui/video.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :json
              :filename "snippets/gorilla-ui/json.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :html
              :filename "snippets/gorilla-ui/html.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :frisk
              :filename "snippets/gorilla-ui/frisk.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :math
              :filename "snippets/gorilla-ui/math.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :aggrid
              :filename "snippets/gorilla-ui/aggrid.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :ansi
              :filename "snippets/gorilla-ui/ansi.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :cytoscape1
              :filename "snippets/gorilla-ui/cytoscape1.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :cytoscape2
              :filename "snippets/gorilla-ui/cytoscape2.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :cytoscape3
              :filename "snippets/gorilla-ui/cytoscape3.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :cytoscape4
              :filename "snippets/gorilla-ui/cytoscape4.edn"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :leaflet-coronado
              :filename "snippets/gorilla-ui/leaflet_coronado.edn"})

(add-snippet {:type  :goldly-clj
              :category :gorilla-ui
              :id :leaflet-london
              :filename "snippets/gorilla-ui/leaflet_london.clj"})

(add-snippet {:type :pinkie
              :category :gorilla-ui
              :id :description-box
              :filename "snippets/gorilla-ui/desc.edn"})

; control

(add-snippet {:type :pinkie
              :category :controls
              :id :button
              :filename "snippets/controls/button.edn"})

(add-snippet {:type :goldly
              :category :controls
              :id :checkbox
              :filename "snippets/controls/checkbox.edn"})

(add-snippet {:type :goldly
              :category :controls
              :id :select
              :filename "snippets/controls/select.edn"})

(add-snippet {:type :goldly-clj
              :category :controls
              :id :dialog
              :filename "snippets/controls/dialog.clj"})

(add-snippet {:type :pinkie
              :category :controls
              :id :progress
              :filename "snippets/controls/progress.edn"})

(add-snippet {:type :goldly
              :category :controls
              :id :text
              :filename "snippets/controls/text.edn"})

(add-snippet {:type :goldly
              :category :controls
              :id :slider
              :filename "snippets/controls/slider.edn"})

(add-snippet {:type :goldly
              :category :controls
              :id :slider-ion
              :filename "snippets/controls/slider_ion.edn"})

(add-snippet {:type :pinkie
              :category :controls
              :id :popover
              :filename "snippets/controls/popover.edn"})

(add-snippet {:type :pinkie
              :category :controls
              :id :tab1
              :filename "snippets/controls/tab1.edn"})

(add-snippet {:type :pinkie
              :category :controls
              :id :tab2
              :filename "snippets/controls/tab2.edn"})

(add-snippet {:type :pinkie
              :category :controls
              :id :gridlayout
              :filename "snippets/controls/gridlayout.edn"})

; realworld

(add-snippet {:type  :goldly-clj
              :category :ui-realworld
              :id :iss-realtime
              :filename "snippets/realworld/leaflet_iss_realtime.clj"})

(add-snippet {:type :goldly-clj
              :category :ui-realworld
              :id :holiday
              :filename "snippets/realworld/holiday.clj"})

; vega

(add-snippet {:type :pinkie
              :category :gorilla-ui-vega
              :id :vega
              :filename "snippets/vega/vega.edn"})

(add-snippet {:type :goldly-clj
              :category :gorilla-ui-vega
              :id :vega-bar
              :filename "snippets/vega/bar.clj"})

(add-snippet {:type :pinkie
              :category  :gorilla-ui-vega
              :id :vega-multiline
              :filename "snippets/vega/multiline.edn"})

(add-snippet {:type :pinkie
              :category  :gorilla-ui-vega
              :id :vega-zoom
              :filename "snippets/vega/zoom.edn"})

(add-snippet {:type  :goldly-clj
              :category  :gorilla-ui-vega
              :id :vega-combo
              :filename "snippets/vega/vega_combo.clj"})
