(ns pinkgorilla.ui.gridlayout
  (:require
   [pinkgorilla.ui.error :refer [error-boundary]]
   ["react-grid-layout" :as ReactGridLayout]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn gridlayout [data & children]
  [error-boundary
   [:<>
    [:link {:rel "stylesheet" :href "react-grid-layout/css/styles.css"}]
    [:link {:rel "stylesheet" :href "react-resizable/css/styles.css"}]
    [:> ReactGridLayout  data
     children]]])
; :>
(register-tag :p/gridlayout gridlayout)

(println "RGL: " ReactGridLayout)