(ns demo.app
  (:require 
   [goldly-server.app :refer [goldly-server-run!]]
    [pinkgorilla.ui.goldly] ; snippets
   ))

(defn -main
  []
  (goldly-server-run! {:profile "watch"
                       :config  "goldly-gorillaui.edn"
                       }))