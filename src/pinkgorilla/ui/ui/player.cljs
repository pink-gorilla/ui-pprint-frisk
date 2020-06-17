(ns pinkgorilla.ui.ui.player
  (:require
   [reagent.core :as r]
   ["react-player" :as rp :refer [ReactPlayer]]
   [pinkie.pinkie :refer-macros [register-component]]))

(defn ^{:category :ui}
  player
  "plays Youtube videos
   usage:
   [:p/player {:url url}]
   
   for more config options, see:
   https://www.npmjs.com/package/react-player
   "
  [options]
  ;[:div ; -player
  [:> rp/default options]) ; {:url url :playing true}

(register-component :p/player player)