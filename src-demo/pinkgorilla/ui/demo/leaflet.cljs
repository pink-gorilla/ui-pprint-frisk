(ns pinkgorilla.ui.demo.leaflet
  (:require
   [pinkgorilla.ui.leaflet :refer [leaflet-map]]
   ["react-leaflet" :refer [Map Marker TileLayer]]))

(def center [51.505, -0.09])

(def rectangle  [[51.49, -0.08]
                   [51.5, -0.06]])

(def polygon [[51.515, -0.09]
                 [51.52, -0.1]
                 [51.52, -0.12]])

(def multiPolygon [[[51.51, -0.12]
                       [51.51, -0.13]
                       [51.53, -0.13]]
                      [[51.51, -0.05]
                       [51.51, -0.07]
                       [51.53, -0.07]]])

(def polyline ^:r [^:r [51.505, -0.09]
            ^:r [51.51, -0.1]
            ^:r [51.51, -0.12]])

(def multi-polyline [[[51.5, -0.1]
                  [51.5, -0.12]
                  [51.52, -0.12]]
                 [[51.5, -0.05]
                  [51.5, -0.06]
                  [51.52, -0.06]]])



(def demo
  [:div.leaflet-container {:style {:width 600 :height 600}}
   [:style ".leaflet-container" "{width: 600px; height: 600px;}"]
   [leaflet-map {:view {:center [51.49, -0.08] :zoom 12}
                 :features ^:r [:marker {:position [51.505, -0.09]}
                                :marker {:position [51.51, -0.12] :popup "wow"}
                                :rectangle {:bounds rectangle}
                                :circle {:center center :fillColor "blue" :radius 200}
                                :polygon {:color "purple" :positions polygon}
                                :polygon {:color "purple" :positions multiPolygon}
                                :line { :color "lime" :positions polyline } 
                                :line {:color "lime" :positions multi-polyline}
                                ]}]])
