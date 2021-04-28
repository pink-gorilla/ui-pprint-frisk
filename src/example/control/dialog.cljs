(ns example.control.dialog
  (:require
   [re-frame.core :as rf]
   [example.example :as example]))

(defn- hello []
  [:div
   {:style {:background-color "white"
            :padding          "16px"
            :border-radius    "6px"
            :text-align "center"}} "Hello modal!"])

(def awesome-dialogs
  [:div
   [:button.bg-green-300
    {:title "Click to show dialog!"
     :on-click #(rf/dispatch [:modal/open [hello] :small])}
    "small dialog!"]

   [:button.bg-blue-300
    {:title "Click to show dialog!"
     :on-click #(rf/dispatch [:modal/open [hello]])}
    "default-size dialog!"]])

(example/add
 :control/dialog
 awesome-dialogs)