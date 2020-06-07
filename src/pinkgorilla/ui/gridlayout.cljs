(ns pinkgorilla.ui.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

; https://github.com/STRML/react-grid-layout

(defn ^{:category :layout}
  gridlayout [data & children]

  [:<>
   [:link {:rel "stylesheet" :href "react-grid-layout/css/styles.css"}]
   [:link {:rel "stylesheet" :href "react-resizable/css/styles.css"}]
   [:> ReactGridLayout data
    children]])

(register-component :p/gridlayout gridlayout)