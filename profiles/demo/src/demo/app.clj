(ns demo.app
  (:require
   [webly.user.app.app :refer [webly-run!]]
    ; side effects
   [demo.routes]
   [pinkgorilla.ui.goldly] ; side-effects snippets
   ))

(defn -main
  [profile-name]
    (webly-run! profile-name))