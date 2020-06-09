(ns pinkgorilla.controls.panel
  (:require
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

; stolen from:
; https://github.com/knipferrc/tails-ui/blob/master/src/components/Panel.re


(defn ^{:category :layout}
  panel
  "a panel displays a seq of components
  has a header"
  [{:keys [color title extra]
    :or {color "grey"
         title  "Panel Title"}}
   & children]
  [:div {:class (str "border-2 border-" color "-light rounded")}
   [:div {:class (str "flex flex-row items-center justify-between w-full border-b-2 border-" color "-light p-2")}
    [:div {:class "text-2xl text-grey-darker"}
     title]
    [:div extra]]
   [:div {:class "p-2"}
      ;(into [] children)
    (into [:div] children)
    ; the div wrapper for the c children is weird.
     ;(map-indexed (fn [i c] ^{:key i} [:div.p c]) children)
    ;children
    ]])

(register-component :p/panel panel)