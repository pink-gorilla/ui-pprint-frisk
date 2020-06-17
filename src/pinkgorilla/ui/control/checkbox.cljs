(ns pinkgorilla.ui.control.checkbox
  (:require
   [pinkie.pinkie :refer-macros [register-component]]))

;todo:
; add options: disabled, label

; refer also to:
;https://github.com/knipferrc/tails-ui/blob/master/src/components/Checkbox.re

(defn ^{:category :control}
  checkbox
  "checkbox that is bound to a key of an external atom"
  [a k]
  [:div {:class "mb-3 pt-0"}
   [:input {:type "checkbox"
            :class "px-2 py-1 placeholder-gray-400 text-gray-700 relative bg-white bg-white rounded text-sm border border-gray-400 outline-none focus:outline-none focus:shadow-outline w-full"
            :checked (if (nil? (k @a)) false (k @a))
            :on-change (fn []
                         (println "onchange checkbox")
                         (swap! a assoc k (not (k @a))))}]])

(register-component :p/checkbox checkbox)


