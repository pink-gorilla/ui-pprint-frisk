(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])

(goldly/def-ui demo-charts
  [{:label "bar-chart" :id "/r/vega/bar-chart.vg.json"}
   {:label "population-pyramid" :id "/r/vega/population-pyramid.vg.json"}
   {:label "tree-layout" :id "/r/vega/tree-layout.vg.json"}
   {:label "tree-map" :id "/r/vega/treemap.vg.json"}
   {:label "force directed layout" :id "/r/vega/force-directed-layout.vg.json"}
   {:label "stock index" :id "/r/vega/stock-index-chart.vg.json"}
   {:label "overview-details" :id "/r/vega/overview-plus-detail.vg.json"}
   {:label "scatterplot interaction" :id "/r/vega/brushing-scatter-plots.vg.json"}
   {:label "unemployment map" :id "/r/vega/county-unemployment.vg.json"}
   {:label "box plot" :id "/r/vega/box-plot.vg.json"}
   {:label "contour" :id "/r/vega/contour-plot.vg.json"}])


(system-start!
 (goldly/system
  {:id :vega-combo
   :state {:vega nil}
   :html [:div
          [:h1 "select the sample vega plot you want to see"]
          [:p/select-a {:nav? false 
                        :items demo-charts 
                        :display :label} state [:vega]]
          [:p "you selected: " (:vega @state)]
          (when-let [id (get-in @state [:vega :id])]
            [:p "id: " id]
            [:p/vega id])]
   :fns {}}
  {:fns {}}))




#_[:div
   [:p/slider :widget :x 0 100 5]
   [:p/slider :widget :y 0 100 5]
   [:p/slider :widget :z 0 100 5]]


