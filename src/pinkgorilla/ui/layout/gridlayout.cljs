(ns pinkgorilla.ui.layout.gridlayout
  (:require
   ["react-grid-layout" :as ReactGridLayout]
   [pinkie.pinkie :refer-macros [register-component]]
   ;[pinkgorilla.ui.config :refer [link-css]]
   ))

; https://github.com/STRML/react-grid-layout


(defn ^{:category :layout}
  gridlayout [data & children]
  [:<>
   ;[link-css "react-grid-layout/css/styles.css"]
   ;[link-css "react-resizable/css/styles.css"]
   [:> ReactGridLayout data
    children]])

(register-component :p/gridlayout gridlayout)