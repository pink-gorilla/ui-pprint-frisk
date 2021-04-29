(ns demo.page.examples
  (:require
   [webly.web.handler :refer [reagent-page]]
   [example.component :refer [example-component]]
   [demo.site :refer [main-with-header demo-header]]))


(defmethod reagent-page :demo/example [{:keys [route-params query-params handler] :as route}]
  (let [category-str (:category route-params)
        category (if category-str
                   (if (= category-str "all")
                     nil
                     (namespace (symbol category-str "foo")))
                   nil)
        ;nsf (namespace :viz/all)
        ]
    (println "category: " category)
    [main-with-header
     demo-header
     [example-component category]]))