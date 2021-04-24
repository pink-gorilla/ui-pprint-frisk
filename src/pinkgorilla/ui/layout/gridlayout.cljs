(ns pinkgorilla.ui.layout.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]
   [pinkie.pinkie :refer-macros [register-component]]))

; https://github.com/STRML/react-grid-layout


(defn ^{:category :layout}
  gridlayout [data & children]
  [:<>
   [:> ReactGridLayout data
    children]])

(register-component :p/gridlayout gridlayout)