(ns pinkgorilla.controls.tailsui
  (:require
   [reagent.core :as r]
   ["tails-ui" :refer [Button Tab Tabs]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(def css "tails-ui/dist/index.css")

(defn button1 []
  [:<>
   [:link {:rel "stylesheet" :href css}]
   [:p "tails ui button:"]
   [:> Button {:color "blue"
               :type "submit"
               :fullWidth true
               :outline true}

    "Submit"]])

(register-tag :p/button1 button1)

(defn tab [options]
  (into [:> Tab options]
        (r/children (r/current-component))))

(defn tabs [& children]
  (into [:> Tabs]
        (map (fn [child]
               (into [:> Tab] (rest child))) children)))

(register-tag :p/tab tab)
(register-tag :p/tabs tabs)


