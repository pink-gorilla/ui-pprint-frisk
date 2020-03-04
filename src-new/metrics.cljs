(ns financials.views.metrics
  (:require
    [clojure.string :as string]
    [reagent.core :as r]
    [thi.ng.strf.core :as f]
    [helper.main :refer [round-number round-number-digits]]
    [comp.table :refer [my-table]])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-rg deftest dom-node]]))

(def table-fields [
  [ :date "Date"]
  [ :open "Open"]
  [ :high "High"]
  [ :low "Low"]
  [ :close "Close" (partial round-number-digits 2)]
  ;[ :close-adj "CloseAdj" (partial round-number-digits 2)]
  [ :volume "Volume"]
  [ [:report :period] "year"]
  [ [:report-metrics :shares-out]  "SharesOut" (partial round-number-digits 1)]
  [ [:report-metrics :shares-adj]  "SharesAdj" (partial round-number-digits 1)]
  [ [:report-metrics :market-cap]  "MC" (partial round-number-digits 1)]
  [ [:report-metrics :turnover-mc]  "t/MC" ]
  [ [:report-metrics :price-sales]  "P/S" (partial round-number-digits 1)]
  [ [:report-metrics :sales-growth]  "S%" ]
  [ [:report-metrics :price-earnings] "P/E" round-number]
  [ [:report-metrics :price-ebit] "P/EBIT" round-number]
  [ [:report-metrics :price-book] "P/B" (partial round-number-digits 1)]
  [ [:report-metrics :dividend-yield] "DvdYld" (partial round-number-digits 1)]
  [ [:report-metrics :ebit-margin] "%EBIT-Margin" (partial round-number-digits 1)]

  [ [:report-metrics :asset-yield] "%AssetYield" (partial round-number-digits 1)]
  [ [:report-metrics :ev] "EV" (partial round-number-digits 1)]
  [ [:report-metrics :ev-ebit] "EV/EBIT" (partial round-number-digits 1)]
  [ [:report-metrics :debt-ebit] "LT-D/EBIT" (partial round-number-digits 1)]
  [ [:report-metrics :ebit] "EBIT" (partial round-number-digits 1)]])


(defn monthly-metrics [series]
  ;[:div ;#financial-metrics-table
   ;[:p (str "Monthly Series (with ratios)" )]
   (if (and (not (nil? series)) (> (count series) 0))
       [my-table table-fields series]
       [:h1 "No Data for Metrics Table"]))

(comment

  [:table [:tbody
           [:tr
            [:th "date"]
            [:th "open"] [:th "high"] [:th "low"] [:th "close"] [:th "volume"]
            [:th "report"]
            [:th "p/s"] [:th "p/e"] [:th "p/ebit"] [:th "dvd%"] [:th "p/b"]]
           (for [row series]
             [:tr {:key (:date row)}                     ; tr is put to a list, therefore needs key prop.
              [:td (:date row)]
              [:td (:open row)]
              [:td (:high row)]
              [:td (:low row)]
              [:td (round-number-digits 2 (:close row))]
              [:td (:volume row)]
              [:td (get-in row [:report :period])]
              [:td (round-number (get-in row [:report-metrics :price-sales]))]
              [:td (round-number (get-in row [:report-metrics :price-earnings]))]
              [:td (round-number (get-in row [:report-metrics :price-ebit]))]
              [:td (round-number (get-in row [:report-metrics :dividend-yield]))]
              [:td (round-number (get-in row [:report-metrics :price-book]))]
              ])]]

  )
