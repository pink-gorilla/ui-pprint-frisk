(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])


;; this system has a bug.

(system-start!
 (goldly/system
  {:id :dialog
   :state nil
   :html [:div
          [:button.bg-green-300
           {:title "Click to show dialog!"
            :on-click #(modal [:h1.bg-blue-300.p-5 "dummy dialog"] :small)}
           "small dialog!"]

          [:button.bg-blue-300
           {:title "Click to show dialog!"
            :on-click #(modal (?hello))}
           "default-size dialog!"]]
   :fns {}
   :fns-raw {:hello (fn []
                      [:div
                       {:style {:background-color "white"
                                :padding          "16px"
                                :border-radius    "6px"
                                :text-align "center"}} "Hello modal!"])}}
  {:fns {}}))
