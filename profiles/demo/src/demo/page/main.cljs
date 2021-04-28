(ns demo.page.main
  (:require
   [webly.web.handler :refer [reagent-page]]))


(defn category [c]
  [:a {:href (str "/examples/" c)}
  [:span.bg-yellow-300.w-12.border.border-round.p-2
   c
   ]])

(defmethod reagent-page :demo/main [{:keys [route-params query-params handler] :as route}]
  [:div.bg-blue-300
   [:p "gorilla-ui has ui components"]
   [:div.bg-blue-300
    [category "viz"]
    [category "control"]
    [category "layout"]
    [category "app"]
    [category "pinkie"]
    
    ]
   [:a {:href "/test"}
    [:p "test page (for development)"]]])
