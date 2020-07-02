(ns pinkgorilla.ui.ui.dialog
  (:require
   [re-frame.core :refer [reg-sub-raw reg-event-db reg-event-fx path dispatch subscribe]])
  (:require-macros [reagent.ratom :refer [reaction]]))

; stolen from:
; https://github.com/benhowell/re-frame-modal

(reg-sub-raw
 :modal
 (fn [db _] (reaction (:modal @db))))

(reg-event-db
 :modal
 (fn [db [_ data]]
   (assoc-in db [:modal] data)))

(defn modal-panel
  [{:keys [child size show?]}]
  [:div {:class "modal-wrapper"}
   [:div {:class "modal-backdrop"
          :on-click (fn [event]
                      (do
                        (dispatch [:modal {:show? (not show?)
                                           :child nil
                                           :size :default}])
                        (.preventDefault event)
                        (.stopPropagation event)))}]
   [:div {:class "modal-child"
          :style {:width (case size
                           :extra-small "15%"
                           :small "30%"
                           :large "70%"
                           :extra-large "85%"
                           "50%")}} child]])

(defn modal-container []
  (let [modal (subscribe [:modal])]
    (fn []
      [:div
       [:link {:rel "stylesheet" :href "/dialog.css"}]
       (if (:show? @modal)
         [modal-panel @modal])])))

(defn- close-modal []
  (dispatch [:modal {:show? false :child nil}]))





