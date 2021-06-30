(ns pinkgorilla.ui.css
  (:require
   [webly.user.css.helper :refer [add-themes]]))

(def themes-aggrid
  ["alpine-dark" "alpine"
   "balham" "balham-dark"
   "blue" "bootstrap"
   "dark" "fresh"
   "material"])

(def fonts-aggrid
  ["Alpine"
   "Balham"
   "Classic"
   "Material"])


;; css config partially moved to edn config


(def components
  {:aggrid  (add-themes
             {true ["ag-grid-community/dist/styles/ag-grid.css"]
              ;"default" ["ag-grid-community/dist/styles/ag-grid.css"]
              }
             "ag-grid-community/dist/styles/ag-theme-%s.css"
             themes-aggrid)})

(def config
  {:aggrid   "balham" ; true 
   })
