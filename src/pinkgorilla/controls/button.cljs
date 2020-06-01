(ns pinkgorilla.controls.button
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

; for ideas for props for button look at:
; https://github.com/knipferrc/tails-ui/blob/master/src/components/Button.re

(defn button
  ([text]
   [button {} text])
  ([{:keys [on-click] :as options} text]
   [:button {:class "bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
             :on-click (fn [_ & _] (when on-click (on-click)))}
    text]))

(register-tag :p/button button)