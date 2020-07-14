(ns pinkgorilla.ui.ui.dialog
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
   [re-frame.core :refer [reg-sub-raw reg-event-db reg-event-fx path dispatch subscribe]]
   [pinkgorilla.ui.config :refer [link-css]]))

; stolen from:
; https://github.com/benhowell/re-frame-modal

(reg-event-db
 :modal
 (fn [db [_ data]]
   (assoc-in db [:modal] data)))

(reg-event-db
 :modal/open
 (fn [db [_ child size]]
   (assoc-in db [:modal]
             {:show? true
              :child child
              :size (or size :default)})))

(reg-event-db
 :modal/close
 (fn [db [_]]
   (if (get-in db [:modal :show?])
     (assoc-in db [:modal] {:show? false
                            :child nil
                            :size :default})
     db)))

#_(reg-event-db
   :dialog/keydown
   (fn [db [_ keycode]]
     (case keycode
       27 (do (close-modal)
              db)
       db)))

(defn modal-panel
  [{:keys [child size]}]
  [:div {:class "modal-wrapper"}
   [:div {:class "modal-backdrop"
          ; keydown not working
          ;:on-key-down #(dispatch [:dialog/keydown (.-which %)])
          :on-click (fn [event]
                      (do
                        (dispatch [:modal/close])
                        ;(close-modal)
                        (.preventDefault event)
                        (.stopPropagation event)))}]
   [:div {:class "modal-child"
          :style {:width (case size
                           :extra-small "15%"
                           :small "30%"
                           :large "70%"
                           :extra-large "85%"
                           "50%")}} child]])

(reg-sub-raw
 :modal
 (fn [db _] (reaction (:modal @db))))

(defn modal-container []
  (let [modal (subscribe [:modal])]
    (fn []
      [:div
       [link-css "gorillaui/dialog.css"]
       (if (:show? @modal)
         [modal-panel @modal])])))







