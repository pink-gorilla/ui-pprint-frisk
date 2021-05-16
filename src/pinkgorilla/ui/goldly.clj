(ns pinkgorilla.ui.goldly
  (:require [systems.snippet-registry :refer [add-snippet]]))

#_(add-snippet {:type :goldly-clj
                :category :goldly-bundel
                :id :holiday-snippet
                :filename "snippets/goldly-bundel/holiday.clj"})

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
              :id :vega
              :filename "snippets/gorilla-ui/vega.edn"})

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