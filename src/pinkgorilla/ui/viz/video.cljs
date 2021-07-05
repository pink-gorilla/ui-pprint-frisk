(ns pinkgorilla.ui.viz.video
  (:require
   ["react-player" :as rp :refer [ReactPlayer]]))

(defn ^{:category :ui}
  video
  "plays Youtube videos
   usage:
   [:p/player {:url url}]
   
   for more config options, see:
   https://www.npmjs.com/package/react-player
   "
  [options]
  ;[:div ; -player
  [:> rp/default options]) ; {:url url :playing true}

