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
              :id :ansi
              :filename "snippets/gorilla-ui/ansi.edn"})

; aggrid

(add-snippet {:type :pinkie
              :category :aggrid
              :id :aggrid-small
              :filename "snippets/aggrid/aggrid_small.edn"})

(add-snippet {:type :goldly-clj
              :category :aggrid
              :id :aggrid-large
              :filename "snippets/aggrid/aggrid_large.clj"})

; cytoscape

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

; leaflet

(add-snippet {:type :pinkie
              :category :leaflet
              :id :leaflet-simpel
              :filename "snippets/leaflet/leaflet_simpel.edn"})

(add-snippet {:type  :goldly-clj
              :category :leaflet
              :id :leaflet-london
              :filename "snippets/leaflet/leaflet_london.clj"})

(add-snippet {:type  :goldly-clj
              :category :leaflet
              :id :leaflet-iss-realtime
              :filename "snippets/leaflet/leaflet_iss_realtime.clj"})

(add-snippet {:type :goldly-clj
              :category :leaflet
              :id :leaflet-holiday
              :filename "snippets/leaflet/holiday.clj"})

