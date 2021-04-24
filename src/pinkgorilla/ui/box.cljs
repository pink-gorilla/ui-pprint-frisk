(ns pinkgorilla.ui.box
  (:require
   [reagent.core :as r]))

(defn size [s]
  ; either both pixels, or both percentage.
  (case  s
    :small  {:style {:width "400px" :height "200px"}}
    :medium {:style {:width "600px" :height "600px"}}
    :full   {:style {:width "100%"  :height "100%"}}
    {:style {:width "600px" :height "150px"}}))

(defn size-selector [size text current set-size!]
  (let [bg (if (= size current)
             "bg-green-400"
             "bg-yellow-400")]
    [:span.border-solid.ml-2
     {:class bg
      :on-click #(set-size! size)} text]))

(defn box [{:keys [s render data]}]
  (let [size-a (r/atom s)
        set-size! (fn [s]
                    (println "size: " s)
                    (reset! size-a s))]
    (fn [{:keys [s render data]}]
      (let [style (size @size-a)]
        [:div
         [:span
          [size-selector :small "sm" @size-a set-size!]
          [size-selector :medium "md" @size-a set-size!]
          [size-selector :full "lg" @size-a set-size!]]

         [:div.bg-blue-300.overflow-hidden  ; in case the renderer ignores our size
          style
          [render (merge data style)]]]))))