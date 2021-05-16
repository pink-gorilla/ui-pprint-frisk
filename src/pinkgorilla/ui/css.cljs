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

(def components
  {:leaflet {true ["leaflet/dist/leaflet.css"]} ; "https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
   :aggrid  (add-themes
             {true ["ag-grid-community/dist/styles/ag-grid.css"]
              ;"default" ["ag-grid-community/dist/styles/ag-grid.css"]
              }
             "ag-grid-community/dist/styles/ag-theme-%s.css"
             themes-aggrid)
   :gridlayout {true ["react-grid-layout/css/styles.css"
                      "react-resizable/css/styles.css"]}
   :tailsui {true ["tails-ui/dist/index.css"]}
   :pydoc {true ["gorilla-ui/pydoc.css"]}})

(def config
  {:leaflet true
   :aggrid   "balham" ; true 
   :gridlayout true
   :tailsui false
   :pydoc true})

(println "gorilla-ui css config: " components)