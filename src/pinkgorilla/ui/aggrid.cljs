(ns pinkgorilla.ui.aggrid
  (:require
   ["ag-grid-react" :as rs :refer [AgGridReact]]
 ; ["ag-grid-community/dist/styles/ag-grid.css"]
 ;  ["ag-grid-community/dist/styles/ag-theme-balham.css"]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

;node_modules/ag-grid-community/dist/styles/

(defn ^{:category :data}
  aggrid [data]
  [:<>
   [:link {:rel "stylesheet" :href "ag-grid-community/dist/styles/ag-grid.css"}]
   [:link {:rel "stylesheet" :href "ag-grid-community/dist/styles/ag-theme-balham.css"}]
   [:> AgGridReact data]])

(register-component :p/aggrid aggrid)
