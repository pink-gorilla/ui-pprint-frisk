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

(defn ^{:category :viz}
  aggrid
  "displays a seq in a table, uses ag-grid
   [aggrid {:columnDefs [{:headerName \"Make\" :field \"make\"}
                         {:headerName \"Model\" :field \"model\"}
                         {:headerName \"Price\" :field \"price\"}]
            :rowData [{:make \"Toyota\" :model \"Celica\" :price 35000}
                      {:make \"Ford\" :model \"Mondeo\" :price 32000}
                      {:make \"Porsche\" :model \"Boxter\" :price 72000}]}]
   "
  [data]
  (let [data-conv (default-cols data)
        ;on-grid-ready
        ;opts (assoc data-conv :onGridReady on-grid-ready)
        ]
    [:> AgGridReact data-conv]))

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