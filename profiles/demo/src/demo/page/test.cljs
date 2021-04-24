(ns demo.page.test
  (:require
   [pinkie.error :refer [error-boundary]]
   [webly.web.handler :refer [reagent-page]]
   [example.data.cytoscape :refer [c-ex]]
   [pinkgorilla.ui.data.highchart :refer [highchart-boxed]]
   [example.data.highchart :refer [highchart-spec]]))

; this page is useful when developing new components

(defmethod reagent-page :demo/test [{:keys [route-params query-params handler] :as route}]
  ;[error-boundary
  [:div.h-screen.w-screen.bg-red-400
  ;[highchart-boxed highchart-spec]
  [c-ex]
   ;
  ]
  )


