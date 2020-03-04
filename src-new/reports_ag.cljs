(ns financials.views.reports-ag
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
    [camel-snake-kebab.extras :as cske]
    )
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-rg deftest dom-node]]))

;; https://ag-grid.com/javascript-grid-properties/

(defn getData
  "gets the data for a row
   row format: {:data []}"
  [index digits params]
  (let [p (js->clj params)
        data (get-in p ["data" "data"])
        data-item (get data index)
        data-item (round-number-digits digits data-item)
        ]
        data-item
   ))

(def colCategory
      {:headerName "category"
       :field "category"
       :width 70
       :sortable true
       :filter true
       :resizable true
       :pinned "left"
       :lockPosition true
       ;:rowGroup true
       })

(def colField
   {:headerName "field"
    :field "name"
    :width 200
    :sortable true
    :filter true
    :resizable true
    :pinned "left"
    :lockPosition true
    })

(defn format-wrapper
  "wrapper to format a cell value
   ag-grid passes {:value v} as js object
   we wrap this so that our formatters can be pure clojurescript functions"
  [formatter js-params] ; formatter is first argument, so it can be partially applied
  (let [p (js->clj js-params)
        v (get p "value")]
       (formatter v)))

(defn make-period-column
  [digits idx period]
      {:headerName period
       :width 80
       :sortable true
       :filter true
       :resizable true
       :valueGetter (partial getData idx digits)
     })

(defn make-columns [data digits]
  (let [periods (:periods data)]
    (into [colCategory colField] (map-indexed (partial make-period-column digits) periods))))


(def ag-adapter (r/adapt-react-class (.-AgGridReact js/agGridReact) ))

(defn financial-reports-ag [data digits];  field-groups]
  [:div {:className "ag-theme-balham"
         :style {
            ;:height "400px"
            :height "100%"
            ;:width "700px"
            :width "100%"
            :color "blue"}}
      [ag-adapter {:columnDefs (make-columns data digits)
                   :rowData (:rows data)   ;  field-groups)
                   :pagination false
                   :paginationAutoPageSize true
                   :enableRangeSelection true
                   :animateRows false
                   }]
                  ]
          )



(defn inject-field [data field-row]
  (let [field (:symbol field-row)
        name (:name field-row)
        category (:category field-row)
        rows (:rows data)]
   {:field field
    :name name
    :data (field rows)
    :category category}))

(defn inject-data [fields data]
  (let [;_ (println "fields are: " fields)
        ;_ (println "data is: " data)
        ]
    {:periods (:periods data)
     :rows (map (partial inject-field data) fields) }))


; DEV-CARD

(def fields- [
  {:symbol :revenue :name "Revenue" :category "PL"}
  {:symbol :ebit :name "EBIT" :category "PL"}
  {:symbol :shares :name "#Shares" :category "SUM"}
  {:symbol :growth-rate :name "Growth-Rate" :category "STATS"}
  ])

(def data- {
  :periods ["2000" "1999" "1998"]
  :rows {
    :revenue [100 110 120]
    :ebit [10 11 12]
    :shares [10 10 9]
  }})

; This format is the result of inject-data
(def data-merged- {
  :periods ["2000" "1999" "1998"]
  :rows [
    {:field :revenue :name "Revenue" :data [100 110 120.123456789]}
    {:field :ebit :name "EBIT" :data [10 11 12]}
    {:field :shares :name "#Shares" :data[10 10 9]}
  ]})


(defcard-rg reports-grid
  ""
  [:div {:style {:width 600 :height 400}}
      ;[financial-reports-ag data-merged- ]
      [financial-reports-ag (inject-data fields- data-) 2]
    ])
