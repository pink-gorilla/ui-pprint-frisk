(ns pinkgorilla.ui.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

; https://github.com/STRML/react-grid-layout

(defn gridlayout [data & children]
  
   [:<>
    [:link {:rel "stylesheet" :href "react-grid-layout/css/styles.css"}]
    [:link {:rel "stylesheet" :href "react-resizable/css/styles.css"}]
    [:> ReactGridLayout data
     children]])

(register-tag :p/gridlayout gridlayout)