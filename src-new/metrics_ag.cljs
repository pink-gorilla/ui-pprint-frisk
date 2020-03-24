(ns financials.views.metrics-ag
  (:require
    [clojure.string :as string]
    [clojure.set]
    [reagent.core :as r]
    [thi.ng.strf.core :as f]
    [helper.main :refer [round-number round-number-digits]]
    [cljsjs.ag-grid-react]
    [financials.views.metrics :refer [table-fields]]
    [comp.mui :refer [keyword->PasCamelCase]]
    [camel-snake-kebab.core :as csk]
    [camel-snake-kebab.extras :as cske])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-rg deftest dom-node]]))

;; https://ag-grid.com/javascript-grid-properties/


;; PREPARE DATA AS NEEDED BY AG-GRID

(defn mykey->js [k]
   (name (keyword->PasCamelCase k))
   ;(name k)
   )


(defn get-data-in
 "gets the data for a row
  row format: {:data []}"
 [location params]
 (let [p (js->clj params) ; convert passed params to clojurescript
       location-array (into [:data] location) ; example : [:data :report-metrics :price-sales]
       location-array (map mykey->js location-array) ;  example: ["data" "reportMetrics" "priceSales"]
       data (get-in p location-array)
       ]
      data
      ))

(defn getDataString
  [field]
  (clojure.string/join "." (map mykey->js (into [:data] field)))
  )


(defn add-field [field row-def]
  (if (vector? field)
      ;(assoc row-def :valueGetter (getDataString field) ) ; field = array -> nested property
      (assoc row-def :valueGetter (partial get-data-in field) ) ; field = array -> nested property
      (assoc row-def :field (name field)) ; field is string = single column
  ))


(defn format-wrapper
  "wrapper to format a cell value
   ag-grid passes {:value v} as js object
   we wrap this so that our formatters can be pure clojurescript functions"
  [formatter js-params] ; formatter is first argument, so it can be partially applied
  (let [p (js->clj js-params)
        v (get p "value")]
      (formatter v)))

(defn add-formatter [formatter row-def]
   (if (nil? formatter)
       row-def
       (assoc row-def :valueFormatter (partial format-wrapper formatter))))


(defn width [field]
  (case field
     :date 100
     60))

(defn add-pin [field row-def]
   (if (= field :date)
       (assoc row-def :pinned "left" :lockPosition true)
       row-def))

(defn convert-row
  "Example conversion:
      [[:report-metrics :price-ebit] \"P/EBIT\" round-number]
  "
  [my-row]
  (let [ [field label formatter] my-row ]  ; my-row is an array with 3 columns
  (->> {:headerName label
       :width (width field)
       :sortable true
       :filter true
       :resizable true}
       (add-field field)
       (add-formatter formatter)
       (add-pin field)
  )))


(defn convert-rows []
   (map convert-row table-fields))

; only convert the row definition once, as this does not change.
(def columnDefs (convert-rows))

(def ag-adapter (r/adapt-react-class (.-AgGridReact js/agGridReact) ))

(defn convert-item [item]
  (let [m (:report-metrics item)
        m (cske/transform-keys csk/->camelCaseString m)
        i (assoc item :report-metrics m)]
     (clojure.set/rename-keys i {:report-metrics :reportMetrics})))



(defn convert-series [series]
   (if (nil? series)
        nil
        (map convert-item series)))


(defn monthly-metrics
  "timeseries with added metrics"
  [series]
  ;[:div ;#financial-metrics-table
   ;[:p (str "Monthly Series (with ratios)" )]
   (if (and (not (nil? series)) (> (count series) 0))
       [:div {:className "ag-theme-balham"
              :style {;:height "400px"
                      :height "100%"
                      ;:width "700px"
                      :color "blue"}
             }
          [ag-adapter {:columnDefs  columnDefs
                      :rowData (convert-series series)
                      ;:pagination false
                      :pagination false
                      :paginationAutoPageSize true
                      }]]
       [:h1 "No Data for Metrics Table"]))
