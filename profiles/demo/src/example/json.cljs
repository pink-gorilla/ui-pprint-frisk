(ns example.json
  (:require
   [re-frame.core :refer [dispatch]]
   [pinkgorilla.ui.ui.dialog :refer [close-modal]]
   [demo.example :as example]))

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


(defn- hello-bootstrap []
  [:div {:class "modal-content panel-danger"}
   [:div {:class "modal-header panel-heading"}
    [:button {:type "button" :title "Cancel"
              :class "close"
              :on-click #(close-modal)}
     [:i {:class "material-icons"} "close"]]
    [:h4 {:class "modal-title"} "Hello Bootstrap modal!"]]
   [:div {:class "modal-body"}
    [:div [:b (str "You can close me by clicking the Ok button, the X in the"
                   " top right corner, or by clicking on the backdrop.")]]]
   [:div {:class "modal-footer"}
    [:button {:type "button" :title "Ok"
              :class "btn btn-default"
              :on-click #(close-modal)} "Ok"]]])



(defn my-awesome-modal-fn []
  [:button
   {:title "Click to show modal!"
    :on-click #(dispatch [:modal {:show? true
                                  :child [hello]
                                  :size :small}])} "Show me the modal!"])



(example/add
 "json"
 [:div.json-demo
  [:p/json data]
  [my-awesome-modal-fn]])
