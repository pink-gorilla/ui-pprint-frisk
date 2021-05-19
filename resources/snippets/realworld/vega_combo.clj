(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])

(goldly/def-ui demo-charts
  [{:label "bar-chart" :id "https://raw.githubusercontent.com/vega/vega/master/docs/examples/bar-chart.vg.json"}
   {:label "population-pyramid" :id "https://vega.github.io/vega/examples/population-pyramid.vg.json"}
   {:label "tree-layout" :id "https://vega.github.io/editor/spec/vega/tree-layout.vg.json"}
   {:label "tree-map" :id "https://vega.github.io/editor/spec/vega/treemap.vg.json"}
   {:label "force directed layout" :id "https://vega.github.io/editor/spec/vega/force-directed-layout.vg.json"}
   {:label "stock index" :id "https://vega.github.io/editor/spec/vega/stock-index-chart.vg.json"}
   {:label "overview-details" :id "https://vega.github.io/editor/spec/vega/overview-plus-detail.vg.json"}
   {:label "scatterplot interaction" :id "https://vega.github.io/editor/spec/vega/brushing-scatter-plots.vg.json"}
   {:label "unemployment map" :id "https://vega.github.io/editor/spec/vega/county-unemployment.vg.json"}
   {:label "box plot" :id "https://vega.github.io/editor/spec/vega/box-plot.vg.json"}
   {:label "contour" :id "https://vega.github.io/editor/spec/vega/contour-plot.vg.json"}])


(system-start!
 (goldly/system
  {:id :vega-combo
   :state {:vega nil}
   :html [:div
          [:h1 "select the sample vega plot you want to see"]
          [:p/pselectm
           {:nav? false}
           demo-charts
           state :vega]
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


