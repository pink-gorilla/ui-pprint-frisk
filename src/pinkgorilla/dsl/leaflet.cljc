(ns pinkgorilla.dsl.leaflet
  "why dsl? 
   create functions/macros that allow to easily create renderable data structures

   The data that gets passed to a renderer has to be verbose, because it allows 
   to configure all options. The dsl allows us to build the data that gets displayed
   in an easy manner.

   dsl stolen from: https://github.com/wiseman/leaflet-gorilla
   - currently not working, because:
     1. we need to calculate map center
     2. wiseman syntax does not allow to specify all params needed

")

(def default-options
  {:width 600
   :height 400

   :zoom 10
   :center [8.5407166 -79.8833319] ; panama, but better to calculate center automatically
   ;{style: {color: '{{color}}',
   ;            weight: {{weight}},
   ;            dashArray: '{{dash-array}}',
   ;            lineCap: '{{line-cap}}',
   ;            lineJoin: '{{line-join}}',
   ;            opacity: {{opacity}}}});
   ; defaults for a new plot that gets added
   :color "steelblue"
   :weight 5.0
   :opacity 1.0
   :dash-array "1, 0"
   :line-cap "butt"
   :line-join "miter"})

;; Draw on Map

(defn- transpose-coord [[lat lon]]
  [lon lat])

(defn- multipoint-feature [coords]
  {:type :Feature
   :geometry {:type :MultiPoint
              :coordinates (map transpose-coord coords)}})

(defn- linestring-feature [coords]
  {:type :Feature
   :geometry {:type :LineString
              :coordinates (map transpose-coord coords)}})

(defn- polygon-feature [coords-arrays]
  {:type :Feature
   :geometry {:type :Polygon
              :coordinates (map #(map transpose-coord %) coords-arrays)}})

;; We use [lat lon], but GeoJSON uses [lon lat].


;; geo-json format

(defmulti geojson-for-geodesc :type)

(defmethod geojson-for-geodesc :geojson [geodesc]
  ;(json/read-str 
  (:desc geodesc)
  ; )
  )

(defmethod geojson-for-geodesc :points [geodesc]
  (multipoint-feature (:desc geodesc)))

(defmethod geojson-for-geodesc :line [geodesc]
  (linestring-feature (:desc geodesc)))

(defmethod geojson-for-geodesc :polygon [geodesc]
  (polygon-feature (:desc geodesc)))

(defn parse-args
  "parses custom syntax for geo-data specification.
   see unit test
   [vec of points] & [:kw1 val1 :kw2 val2]   "
  [args]
  (loop [args args
         geodescs []
         options {}]
    (if (not (seq args))
      [geodescs options]
      (let [arg (first args)
            rstargs (next args)]
        (if (keyword? arg)
          (if (seq rstargs)
            (recur (next rstargs)
                   geodescs
                   (assoc options arg (first rstargs)))
            #?(:clj  (throw (Exception. (str "No value specified for option " arg)))
               :cljs  nil) ; TODO: return exception also in cljs.
            )
          (recur rstargs
                 (conj geodescs arg)
                 options))))))

(defn canonicalize-geodesc [default-type g]
  (let [type-desig (first g)
        desc (second g)
        canon (if (keyword? type-desig)
                {:type type-desig :desc desc}
                {:type default-type :desc g})]
    canon))

(defrecord LeafletView [geodescs opts])

(defn geo
  "Plots geometries on a map."
  [& args]
  (let [[geometries opts] (parse-args args)]
    (LeafletView. (map #(canonicalize-geodesc :points %) geometries) opts)))

(defn geojson
  "Plots geometries on a map."
  [& args]
  (let [[geodescs opts] (parse-args args)]
    (LeafletView. (map #(canonicalize-geodesc :geojson %) geodescs) opts)))

;; TODO

; Leaflet is provider-agnostic, meaning that it doesn’t enforce a particular choice of 
; providers for tiles. You can try replacing mapbox/streets-v11 with mapbox/satellite-v9
; .tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
;    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
;    maxZoom: 18,
;    id: 'mapbox/streets-v11',
;    tileSize: 512,
;    zoomOffset: -1,
;    accessToken: 'your.mapbox.access.token'}
;
;   mymap.on ('click', onMapClick);

 ;pointerIcon = new L.Icon({
 ; iconUrl: '../assets/pointerIcon.svg',
 ; iconRetinaUrl: '../assets/pointerIcon.svg',
 ; iconAnchor: [5, 55],
 ; popupAnchor: [10, -44],
 ; iconSize: [25, 55],
 ; shadowUrl: '../assets/marker-shadow.png',
 ; shadowSize: [68, 95],
 ; shadowAnchor: [20, 92],
;})
;
;
; Tooltip
; attribution: string (optional)
; className: string (optional)
; onClose: () => void (optional)
; onOpen: () => void (optional)