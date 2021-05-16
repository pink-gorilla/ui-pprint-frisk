(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])



; this is our helper function to assemble a highchart object. it contains
; default values that we will use for multiple charts
(defn make-chart-config [data]
  {:chart {:type "line"
           :animation false}
   :title {:text (:title data)}
   :subtitle {:text (:subtitle data)}
   :yAxis {:min 0
           :title {:text ""
                   :align "high"}}
               ;:labels {:overflow "justify"}

   :xAxis {:categories (:labels data)}
   :tooltip {:valueSuffix " %"}
   :plotOptions
   {:series
    {:animation 0
     :label
     {;:pointStart 2010
      :connectorAllowed false}}}
   :legend
   {;:x -40
      ;:y 100
      ;:floating true
      ;:borderWidth 1
      ;:shadow true
    :layout "vertical"
    :align "right"
    :verticalAlign "top"}
   :credits {:enabled false}
   :series (:series data)})

(goldly/def-ui highchart-spec
  (make-chart-config
   {:title "Economic Activity"
    :subtitle "made with Love"
    :labels ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug"]
    :series
    [{:name "Installation" :data [439 523 577 698 931 1131 1333 1175]}
     {:name "Manufacturing" :data [249 244 292 291 390 302 381 404]}
     {:name "Sales & Distribution" :data [117 172 165 191 285 247 321 393]}]}))


(system-start!
 (goldly/system
  {:id :highcharts
   :state {}
   :html [:p/highchart highchart-spec]
   :fns {}}
  {:fns {}}))
