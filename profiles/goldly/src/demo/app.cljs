(ns demo.app
  (:require
   ; side-effects
   [goldly-server.app]
   [pinkgorilla.ui.goldly]
   ))

(defn ^:export start []
  (goldly-server.app/start))