(ns demo.page.test
  (:require
   [pinkie.error :refer [error-boundary]]
   [webly.web.handler :refer [reagent-page]]
   [example.viz.cytoscape :refer [c-ex]]
   [pinkgorilla.ui.viz.highchart :refer [highchart-boxed]]
   [example.viz.highchart :refer [highchart-spec]]))

; this page is useful when developing new components

(defmethod reagent-page :demo/test [{:keys [route-params query-params handler] :as route}]
  ;[error-boundary
  [:div.h-screen.w-screen.bg-red-400
  ;[highchart-boxed highchart-spec]
  [c-ex]
   ;
  ]
  )


