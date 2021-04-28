(ns pinkgorilla.ui.viz.aggrid
  (:require
   [clojure.set :refer [rename-keys]]
   [re-frame.core :as rf]
   ["ag-grid-react" :as rs :refer [AgGridReact]]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.box :refer [size]]))

(defn default-column [k]
  {:headerName (name k)
   :field (name k)})

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
  (let [data-conv (default-cols data)]
    [:> AgGridReact data-conv]))

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


;; https://www.ag-grid.com/
;; 