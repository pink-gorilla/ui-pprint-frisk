(ns pinkgorilla.ui.control.input
  (:require
   [pinkie.pinkie :refer-macros [register-component]]))

; stolen from:
; https://www.creative-tim.com/learning-lab/tailwind-starter-kit/documentation/css/progressbars

(defn ^{:category :control}
  input
  [{:keys [on-change value] :as opts}]
  [:div {:class "mb-3 pt-0"}
   [:input (merge opts
                  {:type "text"
                   :placeholder "Placeholder"
                   :class "px-2 py-1 placeholder-gray-400 text-gray-700 relative bg-white bg-white rounded text-sm border border-gray-400 outline-none focus:outline-none focus:shadow-outline w-full"
                   :value (if (nil? value) "" value)
                   :on-change (fn [e]
                                (let [v (-> e .-target .-value)]
                                  (on-change v)))})]])

