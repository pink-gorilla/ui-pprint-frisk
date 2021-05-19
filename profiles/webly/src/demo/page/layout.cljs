(ns demo.page.layout
  (:require
   [webly.web.handler :refer [reagent-page]]
   [demo.site :refer [main-with-header demo-header]]
   [pinkgorilla.ui.lorem-ipsum :refer [ipsum]]))

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


(defmethod reagent-page :demo/layout [{:keys [route-params query-params handler] :as route}]
  [main-with-header
   demo-header
   [sidebar-layout
      [demo-sidebar]
    [:div.bg-green-400.height-full
      [ipsum 20]]]]
  )


