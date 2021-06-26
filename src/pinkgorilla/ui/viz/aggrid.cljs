(ns pinkgorilla.ui.viz.aggrid
  (:require
   [clojure.set :refer [rename-keys]]
   [re-frame.core :as rf]
   ["ag-grid-react" :as rs :refer [AgGridReact]]
   [pinkgorilla.ui.box :refer [box container-style]]))

;; https://www.ag-grid.com/

(defn default-column [k]
  {:headerName (name k)
   :field (name k)
   :resizable true
   :sortable true
   :filter true})

(defn default-cols [spec]
  (let [spec (rename-keys spec  {:columns :columnDefs
                                 :data :rowData})
        {:keys [columnDefs rowData]} spec]
    (if (and (not columnDefs) rowData)
      (let [row1 (first rowData)
            col-keys (keys row1)
            columns (into []
                          (map default-column col-keys))
            r (assoc spec
                     :columnDefs columns)]
        (println "r:" r)
        r)
      spec)))

(defn on-grid-ready [p]
  ; https://www.ag-grid.com/javascript-grid/column-sizing/
  ; It is possible to have the grid auto size the columns to fill the width by default. 
  ; Do this by calling api.sizeColumnsToFit () on the gridReady event.
  (let [cols  (.. p -columnApi getAllColumns)
        col-ids (into []
                      (map #(. % -colId) cols))]
    (.. p -columnApi (autoSizeColumns (clj->js col-ids) true))))

(defn ^{:category :viz}
  aggrid
  "displays a seq in a table, uses ag-grid"
  [data]
  (let [spec (default-cols data)
        ;spec (assoc spec :onFirstDataRendered on-grid-ready)
        spec (assoc spec :onGridReady on-grid-ready)]
    [:> AgGridReact spec]))

(defn ag-theme-classname [theme]
  (if (= theme true)
    ""
    (str "ag-theme-" theme)))

(defn aggrid-styled [{:keys [size] :as spec}]
  (let [theme (rf/subscribe [:css/theme-component :aggrid])]
    (fn [{:keys [size] :as spec}]
      [:div {:className (ag-theme-classname @theme)
             :style (:style (container-style (or size :small))) ;{:width "400px" :max-width "400px" :height "300px"}
             }
       [aggrid spec]])))

#_(defn ^{:category :data}
    aggrid-boxed
    "reagent component to render highchart-spec via highcharts.js
   Usage:  [:p/highchart spec-as-clj-data]"
    [data]
    [box {:size :small
          :render-fn aggrid-styled
        ;:box-fn highchart-box
          :data data}])

#_(defn aggrid-b [data]
    [:div {:style {:width "400px" :max-width "400px" :height "300px"}}
     [aggrid-styled data]])