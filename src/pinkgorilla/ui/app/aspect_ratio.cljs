(ns pinkgorilla.ui.app.aspect-ratio
  (:require
   [pinkie.pinkie :refer-macros [register-component]]))

; https://www.youtube.com/watch?v=nqNIy8HkEQ8

(defn padding-ratio [f s]
  (let [r (str (* 100 (/ s f)) "%")]
    (println "padding-ratio: " r)
    r))

(defn ^{:category :layout}
  aspect-ratio
  [w h & children]
  (into [:div {:style {:padding-bottom (padding-ratio w h)}}
         children]))

(register-component :p/aspectratio aspect-ratio)
