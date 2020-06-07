(ns pinkgorilla.ui.player
  (:require
   [reagent.core :as r]
   ["react-player" :as rp :refer [ReactPlayer]]
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]))

(defn ^{:category :ui-interface}
  player [url]
  [:div-player
   [:> rp/default {:url url :playing true}]])

(register-component :p/player player)