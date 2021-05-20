(ns pinkgorilla.ui.control.checkbox)

;todo:
; add options: disabled, label

; refer also to:
;https://github.com/knipferrc/tails-ui/blob/master/src/components/Checkbox.re

(defn ^{:category :control}
  checkbox [{:keys [value on-change]}]
  [:input {:type "checkbox"
           :class "pt-0 px-2 py-1 placeholder-gray-400 text-gray-700 relative bg-white bg-white rounded text-sm border border-gray-400 outline-none focus:outline-none focus:shadow-outline w-full"
           :checked (if (nil? value)
                      false
                      value)
           :on-change (fn [e]
                        (let [v (-> e .-target .-checked)]
                          (when on-change
                            (on-change v))))}])





