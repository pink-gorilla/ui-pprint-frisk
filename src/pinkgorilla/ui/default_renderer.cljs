(ns pinkgorilla.ui.default-renderer
  (:require
   ; parent project setups
   [pinkie.default-setup] ; side-effects
   [pinkie.pinkie :refer-macros [register-component]]

    ; viz
   [pinkgorilla.ui.viz.ansi]
   [pinkgorilla.ui.viz.aggrid :refer [aggrid-styled]]
   [pinkgorilla.ui.viz.cytoscape]
   [pinkgorilla.ui.viz.highchart]
   [pinkgorilla.ui.viz.leaflet]
   [pinkgorilla.ui.viz.sparklines]
   [pinkgorilla.ui.viz.math]
   [pinkgorilla.ui.viz.video]
   [pinkgorilla.ui.viz.json]
   [pinkgorilla.ui.viz.frisk]

    ; app
   [pinkgorilla.ui.app.card]

   [pinkgorilla.ui.app.aspect-ratio]

   ; pinkie
   [pinkgorilla.ui.pinkie.components-ui]
   [pinkgorilla.ui.pinkie.pydoc]))

(register-component :p/aggrid aggrid-styled)



