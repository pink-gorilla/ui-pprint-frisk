(ns pinkgorilla.ui.player
  (:require
   [reagent.core :as r]
   ["react-player" :as rp :refer [ReactPlayer]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn player [url]
  [:div-player
   [:> rp/default {:url url :playing true}]])

(register-tag :p/player player)