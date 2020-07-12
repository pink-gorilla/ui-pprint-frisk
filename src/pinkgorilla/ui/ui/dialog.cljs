(ns pinkgorilla.ui.ui.dialog
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
   [re-frame.core :refer [reg-sub-raw reg-event-db reg-event-fx path dispatch subscribe]]
   [pinkgorilla.ui.config :refer [link-css]]))

; stolen from:
; https://github.com/benhowell/re-frame-modal

(reg-sub-raw
 :modal
 (fn [db _] (reaction (:modal @db))))

(reg-event-db
 :modal
 (fn [db [_ data]]
   (assoc-in db [:modal] data)))

(defn close-modal []
  (dispatch [:modal {:show? false
                     :child nil
                     :size :default
                     :close nil}]))

(defn modal-panel
  [{:keys [child size show? close]}]
  [:div {:class "modal-wrapper"}
   [:div {:class "modal-backdrop"
          :on-click (fn [event]
                      (do
                        (when close
                          (dispatch [close]))

                        (close-modal)
                        #_(dispatch [:modal {:show? (not show?)
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
       [link-css "gorillaui/dialog.css"]
       ;[:link {:rel "stylesheet" :href "/dialog.css"}]
       (if (:show? @modal)
         [modal-panel @modal])])))







