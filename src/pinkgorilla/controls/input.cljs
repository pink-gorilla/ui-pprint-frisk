(ns pinkgorilla.controls.input
  (:require
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

; stolen from:
; https://www.creative-tim.com/learning-lab/tailwind-starter-kit/documentation/css/progressbars

(defn ^{:category :control}
  input
  "textbox that is bound to a key of an external atom"
  [a k]
  [:div {:class "mb-3 pt-0"}
   [:input {:type "text"
            :placeholder "Placeholder"
            :class "px-2 py-1 placeholder-gray-400 text-gray-700 relative bg-white bg-white rounded text-sm border border-gray-400 outline-none focus:outline-none focus:shadow-outline w-full"
            :value (if (nil? (k @a)) "" (k @a))
            :on-change (fn [e]
                         (println "onchange input")
                         (let [v (-> e .-target .-value)]
                           (println "new value: " v)
                           (swap! a assoc k v)))}]])

(register-component :p/input input)