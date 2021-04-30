(ns pinkgorilla.ui.viz.leaflet
  (:require
   ["react-leaflet" :refer [MapContainer TileLayer Popup Marker CircleMarker Circle Rectangle Polygon Polyline GeoJSON]]
   ["leaflet" :refer [Icon]]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.config :refer [res-href]]
   [pinkgorilla.dsl.leaflet :refer [default-options]]))


; config cannot be overritten by the user. this is ui renderer configuration


(def config
  {:tile-layer-url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
   ;:tile-layer-url "http://{s}.tile.osm.org/{z}/{x}/{y}.png"
   :attribution "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"})

(defn marker []
  {:icon (Icon. (clj->js {:iconUrl (res-href "leaflet/dist/images/marker-icon.png")
                          :iconRetinaUrl (res-href "leaflet/dist/images/marker-icon-2x.png")
                          :iconAnchor [5, 55]
                          :popupAnchor [10, -44]
                          :iconSize [25, 55]
                          :shadowUrl (res-href "leaflet/dist/images/marker-shadow.png")
                          :shadowSize [68, 95]
                          :shadowAnchor [20, 92]}))})

(defn- log [s] (.log js/console s))

(defn- feature [data-with-type]
  (let [type (:type data-with-type)
        data (dissoc data-with-type :type)]
    ;(log (str "feature: " type data))
    ;(log (merge marker-default data))

    (case type
      :rectangle ^:r [:> Rectangle data] ;  <Rectangle bounds={rectangle} color="black" />
      :circle ^:r [:> Circle data] ;  <Circle center={center} fillColor="blue" radius={200} />
      :line ^:r [:> Polyline data]   ; <Polyline color="lime" positions={polyline} />
      :polygon ^:r [:> Polygon data]  ; <Polygon color="purple" positions={polygon} />
      :marker ^:r [:> Marker (merge (marker) data)        ;  <Marker position= {position} >  <Marker position= {position} icon= {pointerIcon} >
                   (when (not (nil? (:popup data)))
                     ^:r [:> Popup (:popup data)])]  ; <Popup>A pretty CSS3 popup.<br />Easily customizable.</Popup>
      :circlemarker ^:r [:> CircleMarker data   ;  <CircleMarker center= {[51.51, -0.12]} color= "red" radius= {20} >
                         (when (not (nil? (:popup data)))
                           ^:r [:> Popup (:popup data)])]  ;<Popup>Popup in CircleMarker</Popup>
      :geojson ^:r [:> GeoJSON data] ;<GeoJSON  data={london_postcodes} style={this.geoJSONStyle} onEachFeature={this.onEachFeature}
      (do (log (str "No feature found for: " type))
          nil))))


; Map props:
; bounds: bounds (optional): A rectangle for the map to contain. It will be centered, and the map will zoom in as close as it can while still showing the full bounds. Changes are compared using the 🍃 equals() method of LatLngBounds.
; center: latLng (optional if viewport is provided with a center value): Center of the map. Changes are compared by value, so [51.0, 0.0] is considered the same as {lat: 51, lng: 0}.
; className: string (optional): className property of the <div> container for the map.
;onViewportChange: (viewport: {center: ?[number, number], zoom: ?number}) => void (optional): fired continuously as the viewport changes.
;onViewportChanged: (viewport: {center: ?[number, number], zoom: ?number}) => void (optional): fired after the viewport changed.
; style: Object (optional): style property of the <div> container for the map.
; id: string (optional): The ID of the <div> container for the map.


(defn view? [feature]
  (= :view (:type feature)))

(defn assoc-if-exists [m key val]
  (if (nil? val)
    m
    (assoc m key val)))

(defn view-map-props [view]
  (let [{:keys [width height zoom center useFlyTo]} view
        style (when (or width height)
                (-> {}
                    (assoc-if-exists :width width)
                    (assoc-if-exists :height height)))]
    (-> {}
        (assoc-if-exists :style style)
        (assoc-if-exists :zoom zoom)
        (assoc-if-exists :center center)
        (assoc-if-exists :useFlyTo useFlyTo))))

;   handleClick = () => {
;    const map = this.mapRef.current
;    if (map != null) {
;      map.leafletElement.locate()
;    }
;  }

;  handleLocationFound = (e: Object) => {
;    this.setState({
;      hasLocation: true,
;      latlng: e.latlng,
;    })


(defn ^{:category :data}
  leaflet-map
  "displays a map with leaflet.
   example:
  [:p/leaflet
   [{:type :view :center [51.49, -0.08] :zoom 12 :height 600 :width 700}
    {:type :rectangle :bounds rectangle}
    {:type :circle :center center :fillColor :blue :radius 200}
    {:type :polygon :positions polygon :color :purple}
    {:type :polygon :positions multiPolygon :color :purple}
    {:type :line :positions polyline :color :lime}
    {:type :line :positions multi-polyline :color :lime}
    {:type :marker :position [51.505, -0.09]}
    {:type :marker :position [51.51, -0.12] :popup \"wow\"}
    {:type :circlemarker :center [51.52, -0.06] :fillColor :blue :radius 200 :popup \"square the circle\"}
    {:type :geojson :data geojson}]]"

  ([options features-incl-view]
   (let [{:keys [css tile-layer-url attribution]} config ; config cannot be set by user
         {:keys [width height zoom center]} options
         view (first (filter view? features-incl-view))
        ; _ (log (str "view:" view))
         features (remove view? features-incl-view)
         view-map (view-map-props view)
        ; (partition 2 features)
         ;_ (log (str "features:" features))
        ;_ (log (str "map prop:" view-map))
         ;_ (log (str "view:" view))
         ;{:keys [view]} data
         ]
     [:div.z-10
      [:> MapContainer (merge  {:zoom zoom
                                :center center
                                :style {:width width :height height}
                                :keyboard true ; navigate map with arrows and +-
                                :class "z-10"
                       ;:ref {this.mapRef}
                       ;:onClick {this.handleClick}
                       ;:onLocationfound= {this.handleLocationFound}
                                }
                               view-map)
;{:center marker-position :zoom zoom}
       [:> TileLayer
        {:url tile-layer-url
         :attribution attribution}]
       ;[:> Marker {:position [51.505, -0.09]}]
       (into  [:<>]
              (map feature features))]]))
  ([features]
   (leaflet-map default-options features)))

(defn leaflet-map-default [data]
  (let [{:keys [features]} data]
    ;(println "features: " features)
    [leaflet-map default-options features]))

(register-component :p/leaflet leaflet-map-default)