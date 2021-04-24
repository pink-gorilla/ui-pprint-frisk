(ns pinkgorilla.ui.css
  (:require
   ;[webly.user.css.helper :refer [add-themes]]
   [goog.string :as gstring]
   [goog.string.format]))

(defn add-themes [m theme-base themes]
  (let [theme-link (fn [theme]
                     (gstring/format theme-base theme))
        add-theme (fn [acc theme]
                    (println "adding:" theme)
                    (assoc acc theme [(theme-link theme)]))]
    (reduce add-theme m themes)))

(def themes-aggrid
  ["balham" "balham-dark"
   "blue" "bootstrap" "dark" "fresh"
   "material"])

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
   :tailsui {true ["tails-ui/dist/index.css"]}})

(def config
  {:leaflet true
   :aggrid   "balham" ; true 
   :gridlayout true
   :tailsui false})

(println "gorilla-ui css config: " components)