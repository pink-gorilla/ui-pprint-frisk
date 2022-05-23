(ns ui.video
  (:require
   ["react-player" :as rp]))

(defn ^{:category :ui}
  video
  "plays Youtube videos
   usage:
   [:p/player {:url url :playing false}]
   
   for more config options, see:
   https://www.npmjs.com/package/react-player
   "
  [props]
  [:> rp/default props]) ; {:url url :playing true}

