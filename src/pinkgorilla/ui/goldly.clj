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

; realworld

(add-snippet {:type  :goldly-clj
              :category :ui-realworld
              :id :iss-realtime
              :filename "snippets/realworld/leaflet_iss_realtime.clj"})

(add-snippet {:type :goldly-clj
              :category :ui-realworld
              :id :holiday
              :filename "snippets/realworld/holiday.clj"})

