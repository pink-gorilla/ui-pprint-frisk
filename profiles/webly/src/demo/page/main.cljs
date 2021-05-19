(ns demo.page.main
  (:require
   [webly.web.handler :refer [reagent-page]]))


(defn box [text link]
   [:a {:href link}
    [:span.bg-yellow-300.w-12.border.border-round.p-2
     text]]
  )


(defmethod reagent-page :demo/main [{:keys [route-params query-params handler] :as route}]
  [:div.bg-blue-300
    [box "layout" "/layout" ]
    [box "leaflet" "/leaflet"]
    ]
   )
