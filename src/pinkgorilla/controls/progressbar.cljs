(ns pinkgorilla.controls.progressbar
  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

; stolen from:
; https://www.creative-tim.com/learning-lab/tailwind-starter-kit/documentation/css/progressbars

(defn progressbar [percent]
  [:div {:class "relative pt-1"}
   [:div {:class "overflow-hidden h-2 mb-4 text-xs flex rounded bg-pink-200"}
    [:div {:style {:width (str percent "%")}
           :class "shadow-none flex flex-col text-center whitespace-nowrap text-white justify-center bg-pink-500"}]]])

(register-tag :p/progressbar progressbar)

