(ns pinkgorilla.ui.aggrid
  (:require
   [reagent.core :as r]
   ["ag-grid-react" :as rs :refer [AgGridReact]]
 ; ["ag-grid-community/dist/styles/ag-grid.css"]
 ;  ["ag-grid-community/dist/styles/ag-theme-balham.css"]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn aggrid [data]
  [:<>
   [:link {:rel "stylesheet" :href "https://cdnjs.cloudflare.com/ajax/libs/ag-grid/21.2.1/styles/ag-grid.css"}]
   [:link {:rel "stylesheet" :href "https://cdnjs.cloudflare.com/ajax/libs/ag-grid/21.2.1/styles/ag-theme-balham.css"}]
   [:> AgGridReact data]])

(register-tag :p/aggrid aggrid)
