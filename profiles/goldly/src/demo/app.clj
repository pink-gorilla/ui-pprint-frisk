(ns demo.app
  (:require 
   [goldly-server.app :refer [goldly-server-run!]]
   [pinkgorilla.ui.goldly] ; ui renderer config + snippets
   ))

(defn -main
  []
  (goldly-server-run! {:profile "watch"
                       :config  "goldly-gorillaui.edn"
                       ;:config {:goldly {:extensions [['pinkgorilla.ui.goldly]]}}
                       }))

