(ns demo.page.main
  (:require
   [webly.web.handler :refer [reagent-page]]))


(defn box [text link]
   [:a {:href link}
    [:span.bg-yellow-300.w-12.border.border-round.p-2
     text]]
  )

(defn category [c]
  [box c (str "/examples/" c)])

(defmethod reagent-page :demo/main [{:keys [route-params query-params handler] :as route}]
  [:div.bg-blue-300
   [:p.mb-3 "gorilla-ui has ui components"]
   [:div.bg-blue-300
    [category "viz"]
    [category "control"]
    [category "layout"]
    [category "app"]
    [category "pinkie"]
    [box "test page (for development)" "/test" ]
    ]
   ])
