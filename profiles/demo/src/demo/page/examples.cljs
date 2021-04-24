(ns demo.page.examples
  (:require
   [webly.web.handler :refer [reagent-page]]
   [example.core :refer [example-component]]))

(defmethod reagent-page :demo/main [{:keys [route-params query-params handler] :as route}]
  [example-component])

(defmethod reagent-page :demo/viz [{:keys [route-params query-params handler] :as route}]
  (let [nsf (namespace :viz/all)]
    ;(println "nsf: " nsf)
    [example-component nsf]))