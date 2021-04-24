(ns example.ui.json
  (:require
   [re-frame.core :refer [dispatch]]
   [example.example :as example]))

(def data
  {:title "Economic Activity"
   :subtitle "made with Love"
   :labels ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug"]
   :series
   [{:name "Installation" :data [439 523 577 698 931 1131 1333 1175]}
    {:name "Manufacturing" :data [249 244 292 291 390 302 381 404]}
    {:name "Sales & Distribution" :data [117 172 165 191 285 247 321 393]}]})

(defn- hello []
  [:div
   {:style {:background-color "white"
            :padding          "16px"
            :border-radius    "6px"
            :text-align "center"}} "Hello modal!"])

(defn awesome-dialogs []
  [:div
   [:button.bg-green-300
    {:title "Click to show dialog!"
     :on-click #(dispatch [:modal/open [hello] :small])}
    "small dialog!"]

   [:button.bg-blue-300
    {:title "Click to show dialog!"
     :on-click #(dispatch [:modal/open [hello]])}
    "default-size dialog!"]])

(example/add
 :controls/dialog
 [awesome-dialogs])

(example/add
 :viz/json
 [:p/json data])
