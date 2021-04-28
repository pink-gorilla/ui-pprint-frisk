(ns demo.page.examples
  (:require
   [webly.web.handler :refer [reagent-page]]
   [example.component :refer [example-component]]))


(defmethod reagent-page :demo/example [{:keys [route-params query-params handler] :as route}]
  (let [category-str (:category route-params)
        category (if category-str
            (if (= category-str "all")
              nil
              (namespace (symbol category-str"foo"))
              )
            nil
            )
        ;nsf (namespace :viz/all)
        ]
    (println "category: " category)
    [example-component category]))