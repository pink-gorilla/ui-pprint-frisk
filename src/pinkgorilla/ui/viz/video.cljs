(ns pinkgorilla.ui.viz.video
  (:require
   ["react-player" :as rp]
   [taoensso.timbre :as timbre :refer [info]]
   [pinkie.box :refer [apply-style]]))

(defn ^{:category :ui}
  video
  "plays Youtube videos
   usage:
   [:p/player {:url url :playing false}]
   
   for more config options, see:
   https://www.npmjs.com/package/react-player
   "
  [props]
  (let [m (apply-style props)]
    (info "player props: " m)
    [:> rp/default m])) ; {:url url :playing true}

