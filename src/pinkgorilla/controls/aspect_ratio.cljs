(ns pinkgorilla.controls.aspect-ratio
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

; https://www.youtube.com/watch?v=nqNIy8HkEQ8

(defn padding-ratio [f s]
  (let [r (str (* 100 (/ s f)) "%")]
    (println "padding-ratio: " r)
    r))

(defn aspect-ratio
  [w h & children]
  [:div {:style {:padding-bottom (padding-ratio w h)}}
   children])

(register-tag :p/aspectratio aspect-ratio)
