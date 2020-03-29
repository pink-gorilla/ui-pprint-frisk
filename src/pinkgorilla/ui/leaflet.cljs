(ns pinkgorilla.ui.leaflet
  (:require
   ["react-leaflet" :refer [Map TileLayer Popup Marker CircleMarker Circle Rectangle Polygon Polyline]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.leaflet.core :refer [default-options config]]))

(defn log [s] (.log js/console s))

(defn feature [[type data]]
  (log (str "feature: " type data))
  (case type
    :rectangle ^:r [:> Rectangle data] ;  <Rectangle bounds={rectangle} color="black" />
    :circle ^:r [:> Circle data] ;  <Circle center={center} fillColor="blue" radius={200} />
    :line ^:r [:> Polyline data]   ; <Polyline color="lime" positions={polyline} />
    :polygon ^:r [:> Polygon data]  ; <Polygon color="purple" positions={polygon} />
    :marker ^:r [:> Marker data        ;  <Marker position= {position} >
                 (when (not (nil? (:popup data)))
                   ^:r [:> Popup (:popup data)])]  ; <Popup>A pretty CSS3 popup.<br />Easily customizable.</Popup>
    :circlemarker ^:r [:> CircleMarker data   ;  <CircleMarker center= {[51.51, -0.12]} color= "red" radius= {20} >
                       (when (not (nil? (:popup data)))
                         ^:r [:> Popup (:popup data)])]  ;<Popup>Popup in CircleMarker</Popup>
    (do (log (str "No feature found for: " type))
        nil)))


(defn leaflet-map
  ([options data]
   (let [{:keys [width height attribution]} options
         {:keys [view features]} data
         {:keys [css tile-layer-url]} config
         feature-list (partition 2 features)
         _ (log (str "features:" features))
         _ (log (str "feature-list:" feature-list))
          _ (log (str "view:" view))
         ;{:keys [view]} data
         ]
     [:div-mymap {:style {:width width :height height}}

      [:link {:rel "stylesheet" :href css}]
      [:> Map view ;{:center marker-position :zoom zoom}
       [:> TileLayer
        {:url tile-layer-url
         :attribution attribution}]
       ;[:> Marker {:position [51.505, -0.09]}]
       (into  [:<>]
             (map feature feature-list))]]))
  ([data]
   (leaflet-map default-options data)))








(register-tag :map leaflet-map)