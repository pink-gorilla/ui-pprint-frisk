(ns pinkgorilla.leaflet.core)

; config cannot be overritten by the user. this is ui renderer configuration
(def config
  {:css "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
   :tile-layer-url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
   ;:tile-layer-url "http://{s}.tile.osm.org/{z}/{x}/{y}.png"
   })

(def default-options
  {:width 400
   :height 400
   :zoom 10
   :attribution "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
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

