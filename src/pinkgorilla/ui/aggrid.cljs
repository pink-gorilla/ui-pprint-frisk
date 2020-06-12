(ns pinkgorilla.ui.aggrid
  (:require
   ["ag-grid-react" :as rs :refer [AgGridReact]]
 ; ["ag-grid-community/dist/styles/ag-grid.css"]
 ;  ["ag-grid-community/dist/styles/ag-theme-balham.css"]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.config :refer [link-css]]))

;node_modules/ag-grid-community/dist/styles/


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
   [link-css "ag-grid-community/dist/styles/ag-grid.css"]
   [link-css "ag-grid-community/dist/styles/ag-theme-balham.css"]
   [:> AgGridReact data]])

(register-component :p/aggrid aggrid)
