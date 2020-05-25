(ns example.aggrid
  (:require
   [thi.ng.strf.core :as f]
   [demo.example :as example]
   [pinkgorilla.ui.aggrid :refer [aggrid]]))

(def columnDefs [{:headerName "Make" :field "make"}
                 {:headerName "Model" :field "model"}
                 {:headerName "Price" :field "price"}])

(def 	rowData [{:make "Toyota" :model "Celica" :price 35000}
               {:make "Ford" :model "Mondeo" :price 32000}
               {:make "Porsche" :model "Boxter" :price 72000}])

(def rates
  [{:tenor "1D" :rate 1 :vol-factor 1 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "5D" :rate 3 :vol-factor 1.5 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "1M" :rate 6 :vol-factor 0.9444443333 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "6M" :rate 4 :vol-factor 2.1234567890123456789 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "1Y" :rate 5 :vol-factor 3 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "2Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "5Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "10Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "30Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "50Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "60Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "70Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "80Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}
   {:tenor "90Y" :rate 2 :vol-factor 2 :s "asdköfj adkfj alkdsfj löasfj ölkdjaf löksdfj löa f" :metrics {:a 1} :report-metrics {:a 1}}])

; ROUND NUMBER

(defn round-number
  [number]
  (if (nil? number) "" (f/format  [(f/float 0)] number)))

(defn round-number-digits
  [digits number] ; digits is first parameter, so it can easily be applied (data last)
  (if (nil? number) "" (f/format  [(f/float digits)] number)))

(defn currency-formatter [params]
  (let [p (js->clj params)
        v (get p "value")]
    (do ;(println "params are: " p)
       ;(println "value: "  v)
      (round-number-digits 2 v))))

(def rateCols [{:headerName "MyTenor" :field "tenor" :width 100 :sortable true :filter true :lockPosition true :pinned "left"}
               {:headerName "Metric/A" :valueGetter "data.metrics.a" :width 50 :sortable false :filter false}
               {:headerName "Rate" :field "rate" :width 50 :sortable true :filter false}
               {:headerName "Vol-F" :field "vol-factor" :width 50 :valueFormatter currency-formatter :sortable false :filter false}
               {:headerName "Comment" :field "s" :width 300 :sortable false :filter false}])


(example/add
 "aggrid"
  [:div.aggrid-demo ; .aggrid-container

   [:h1 "simple demo:"]
   [:div {:style {:height "150px"
                  :width "600px"}}
    [aggrid {:columnDefs columnDefs
             :rowData rowData}]]

   [:h1 "complex demo:"]
   [:div {:className "ag-theme-balham"
          :style {; either both pixels, or both percentage.
                  :height "400px"
                  :width "600px"
                 ;:height "100%"
                 ;:width "100%"
                  :color "blue"}}
    [aggrid {:columnDefs  rateCols
             :rowData rates
             :pagination true
             :paginationAutoPageSize true}]]])
