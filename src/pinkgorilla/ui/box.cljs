(ns pinkgorilla.ui.box
  (:require
   [reagent.core :as r]
   ["react-full-screen" :refer [FullScreen useFullScreenHandle]]))


; https://www.npmjs.com/package/react-full-screen

; (println "fs: " FullScreen)


(defn size [s]
  ; either both pixels, or both percentage.
  (case  s
    :small  {:style {:width "400px" :max-width "400px" :height "300px" :width-px 400 :height-px 300}}
    :medium {:style {:width "600px" :max-width "600px" :height "500px" :width-px 600 :height-px 500}}
    :large    {:style {:width "100%"  :max-width "100%" :height "100%"}}
    :full   {:style {:width "100%"  :max-width "100%" :height "100%"
                     :position "absolute"
                     :top 0
                     :left 0
                     :bottom 0
                     :right 0
                     :z-index 5000}}
    {:style {:width "600px" :max-width "600px" :height "150px"  :width-px 600 :height-px 500}}))

(defn size-selector [size text current set-size!]
  (let [bg (if (= size current)
             "bg-green-400"
             "bg-yellow-400")]
    [:span.border-solid.ml-2
     {:class bg
      :on-click #(set-size! size)} text]))

(defn box-default [data box]
  (let [b  (merge data box)]
    (println "box default: " b)
    b))

(defn box [{:keys [s render data box]}]
  (let [size-a (r/atom s)
        set-size! (fn [s]
                    (println "size: " s)
                    (reset! size-a s))
         ;handle (useFullScreenHandle)  ; hooks need :f> -  https://github.com/reagent-project/reagent/blob/master/doc/ReactFeatures.md
         ;_ (println "handle: " handle)
        ]
    (fn [{:keys [s render data box]}]
      (let [style (size @size-a)
            box (or box box-default)]
        [:div
         [:span
          [size-selector :small "sm" @size-a set-size!]
          [size-selector :medium "md" @size-a set-size!]
          [size-selector :large "lg" @size-a set-size!]
          [size-selector :full "fs" @size-a set-size!]]

         [:div.bg-blue-300.overflow-hidden  ; in case the renderer ignores our size
          style
          [render (box data style)]]]))))