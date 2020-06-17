(ns pinkgorilla.pinkie
  (:require
   [pinkie.pinkie :refer [tag-inject convert-style-as-strings-to-map convert-render-as]]
   ;[pinkgorilla.ui.widget :refer [resolve-widget]]
   ))

(defn pinkie [{:keys [map-keywords widget]} component]
  (let [component (convert-render-as component)
        component (if map-keywords (tag-inject component) component)
        ;component (if widget (resolve-widget component) component)
        component (if map-keywords (convert-style-as-strings-to-map component) component)]
    [:div.reagent-output component]))