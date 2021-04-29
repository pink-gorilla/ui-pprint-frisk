(ns demo.page.test
  (:require
   [pinkie.error :refer [error-boundary]]
   [webly.web.handler :refer [reagent-page]]
   [demo.site :refer [main-with-header demo-header]]
   [example.viz.cytoscape :refer [c-ex]]
   [pinkgorilla.ui.viz.highchart :refer [highchart-boxed]]
   [example.viz.highchart :refer [highchart-spec]]
   [pinkgorilla.ui.lorem-ipsum :refer [ipsum]]))

; this page is useful when developing new components


(defn sidebar-layout [sidebar main]
   [:div {:style {:display "grid"
                  :height "100%"
                  :grid-template-columns "15vh 1fr"
                  :grid-template-rows "1fr"
                  }}
    sidebar
   [:div.overflow-auto.m-0.p-0
    {:style {:height "100%"
             :max-height "100%"}}
    main]])

(defn p [i]
  [:p (str "xxx-" i)])

(defn demo-sidebar []
  (into [:div.bg-red-200 
         ]
        (map p (range 20))))


(defmethod reagent-page :demo/test [{:keys [route-params query-params handler] :as route}]
  ;[error-boundary
  ;[:div.h-screen.w-screen.bg-red-400
  ;[highchart-boxed highchart-spec]
  ;[c-ex]

  [main-with-header
  demo-header
   
   #_[:div.bg-green-400.height-full
      [ipsum 20]]
   [sidebar-layout
      [demo-sidebar]
    [:div.bg-green-400.height-full
      [ipsum 20]]]]
  



   ;[:div "def"]
  )


