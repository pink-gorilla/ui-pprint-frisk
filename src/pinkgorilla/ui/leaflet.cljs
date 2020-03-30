(ns pinkgorilla.ui.leaflet
  (:require
   ["react-leaflet" :refer [Map TileLayer Popup Marker CircleMarker Circle Rectangle Polygon Polyline GeoJSON]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.leaflet.core :refer [default-options]]))


; config cannot be overritten by the user. this is ui renderer configuration
(def config
  {:css "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
   :tile-layer-url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
   ;:tile-layer-url "http://{s}.tile.osm.org/{z}/{x}/{y}.png"
   })



(defn- log [s] (.log js/console s))

(defn- feature [[type data]]
  (log (str "feature: " type data))
  (case type
    :rectangle ^:r [:> Rectangle data] ;  <Rectangle bounds={rectangle} color="black" />
    :circle ^:r [:> Circle data] ;  <Circle center={center} fillColor="blue" radius={200} />
    :line ^:r [:> Polyline data]   ; <Polyline color="lime" positions={polyline} />
    :polygon ^:r [:> Polygon data]  ; <Polygon color="purple" positions={polygon} />
    :marker ^:r [:> Marker data        ;  <Marker position= {position} >  <Marker position= {position} icon= {pointerIcon} >
                 (when (not (nil? (:popup data)))
                   ^:r [:> Popup (:popup data)])]  ; <Popup>A pretty CSS3 popup.<br />Easily customizable.</Popup>
    :circlemarker ^:r [:> CircleMarker data   ;  <CircleMarker center= {[51.51, -0.12]} color= "red" radius= {20} >
                       (when (not (nil? (:popup data)))
                         ^:r [:> Popup (:popup data)])]  ;<Popup>Popup in CircleMarker</Popup>
    :geojson ^:r [:> GeoJSON data] ;<GeoJSON  data={london_postcodes} style={this.geoJSONStyle} onEachFeature={this.onEachFeature}
    (do (log (str "No feature found for: " type))
        nil)))


; Map props:
; bounds: bounds (optional): A rectangle for the map to contain. It will be centered, and the map will zoom in as close as it can while still showing the full bounds. Changes are compared using the 🍃 equals() method of LatLngBounds.
; center: latLng (optional if viewport is provided with a center value): Center of the map. Changes are compared by value, so [51.0, 0.0] is considered the same as {lat: 51, lng: 0}.
; className: string (optional): className property of the <div> container for the map.
;onViewportChange: (viewport: {center: ?[number, number], zoom: ?number}) => void (optional): fired continuously as the viewport changes.
;onViewportChanged: (viewport: {center: ?[number, number], zoom: ?number}) => void (optional): fired after the viewport changed.
; style: Object (optional): style property of the <div> container for the map.
; id: string (optional): The ID of the <div> container for the map.

(defn leaflet-map
  ([options data]
   (let [{:keys [css tile-layer-url]} config ; config cannot be set by user
         {:keys [width height attribution]} options
         {:keys [view features]} data
         
         feature-list (partition 2 features)
         _ (log (str "features:" features))
         _ (log (str "feature-list:" feature-list))
         _ (log (str "view:" view))
         ;{:keys [view]} data
         ]
     [:<>
      [:link {:rel "stylesheet" :href css}]
      [:> Map (merge view  {:style {:width width :height height}}) 
;{:center marker-position :zoom zoom}
       [:> TileLayer
        {:url tile-layer-url
         :attribution attribution}]
       ;[:> Marker {:position [51.505, -0.09]}]
       (into  [:<>]
              (map feature feature-list))]]
   ))
  ([data]
   (leaflet-map default-options data)))








(register-tag :map leaflet-map)