(ns pinkgorilla.ui.data.aggrid
  (:require
   [re-frame.core :as rf]
   ["ag-grid-react" :as rs :refer [AgGridReact]]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.size :refer [size]]))

(defn ^{:category :data}
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
  [:<>
   [:> AgGridReact data]])

(defn ag-theme-classname [theme]
  (if (= theme true)
    ""
    (str "ag-theme-" theme)))

(defn aggrid-styled [data]
  (let [theme (rf/subscribe [:css/theme-component :aggrid])]
    (fn [data]
      [:div (merge
             {:className (ag-theme-classname @theme)} ;(str "ag-theme-balham"
             (size (:size data)))
       [aggrid data]])))

(register-component :p/aggrid aggrid-styled)
