(ns pinkgorilla.ui.ui.popover
  "   popover
     placement: left, yop, right, bottom
   "
  (:require
   [reagent.core :as r]
   ["@popperjs/core" :refer [createPopper]]
   [pinkie.pinkie :refer-macros [register-component]]))

; stolen from:
; https://www.creative-tim.com/learning-lab/tailwind-starter-kit/documentation/react/popovers/left

; ref handling from:
; https://gist.github.com/pesterhazy/4d9df2edc303e5706d547aeabe0e17e1

(defn ^{:category :ui}
  tooltip [{:keys [title content color]}]
  [:div
   [:div {:class (str "bg-" color "-600 text-white opacity-75 font-semibold p-3 mb-0 border-b border-solid border-gray-200 uppercase rounded-t-lg")}
    title]
   [:div {:class "text-white p-3"}
    content]])

(defn ^{:category :ui}
  popover [{:keys [color placement button-text]} child]
  (let [popover? (r/atom false)
        btn (r/atom nil)
        popover (r/atom nil)
        open-popover (fn []
                       (println "showing popover")
                       (println "btn:" @btn)
                       (println "popover: " @popover)
                       (println "placement: " placement)
                       (println "popper: " createPopper)
                       (createPopper @btn @popover #js {:placement placement})  ; "left"
                       (reset! popover? true))
        close-popover (fn [] (reset! popover? false))]
    (fn []
      [:<>
       [:div {:class "flex flex-wrap"}
        [:div {:class "w-full text-center"}
         [:button {:className (str "bg-" color "-500 text-white "
                                   "active:bg-" color "-600 "
                                   "font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1")
                   :type "button"
                 ; :style {:transition "all .15s ease"}
                   :on-click #(if @popover? (close-popover) (open-popover))
                   :ref #(reset! btn %)
                 ;:left color
                   }button-text]
         [:div {:class (str
                        (when-not @popover? "hidden ")
                        "bg-" color "-600 border-0 mr-3 block z-50 font-normal leading-normal text-sm max-w-xs text-left no-underline break-words rounded-lg")
                :ref  #(reset! popover %)}
          child]]]])))

(register-component :p/tooltip tooltip)
(register-component :p/popover popover)

