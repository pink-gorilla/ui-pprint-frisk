(ns pinkgorilla.ui.default-renderer
  (:require
   ; parent project setups
   [pinkie.default-setup] ; side-effects
   [pinkie.pinkie :refer-macros [register-component]]

    ; viz
   [pinkgorilla.ui.viz.aggrid :refer [aggrid-styled]]
   [pinkgorilla.ui.viz.cytoscape :refer [cytoscape cytoscape-boxed]]
   [pinkgorilla.ui.viz.leaflet :refer [leaflet-map]]
   [pinkgorilla.ui.viz.sparklines :refer [sparkline sparkline-bar sparkline-spot]]

   [pinkgorilla.ui.viz.video]
   [pinkgorilla.ui.viz.json]
   [pinkgorilla.ui.viz.frisk]
   [pinkgorilla.ui.viz.ansi]

    ; app
   [pinkgorilla.ui.app.card]
   [pinkgorilla.ui.app.aspect-ratio]

   ; pinkie
   [pinkgorilla.ui.pinkie.components-ui]
   [pinkgorilla.ui.pinkie.pydoc]))

(register-component :p/aggrid aggrid-styled)

(register-component :p/leaflet leaflet-map)

;(register-component :p/cytoscape-raw cytoscape)
(register-component :p/cytoscape cytoscape-boxed)

(register-component :p/sparkline sparkline)
(register-component :p/sparklinebar sparkline-bar)
(register-component :p/sparklinespot sparkline-spot)