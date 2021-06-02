(ns pinkgorilla.ui.default-renderer
  (:require
   ; parent project setups
   [pinkie.default-setup] ; side-effects
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.control.bind :refer [bind]]

    ; viz
   [pinkgorilla.ui.viz.ansi]
   [pinkgorilla.ui.viz.aggrid :refer [aggrid-styled]]
   [pinkgorilla.ui.viz.cytoscape]
   [pinkgorilla.ui.viz.highchart]
   [pinkgorilla.ui.viz.leaflet]
   [pinkgorilla.ui.viz.sparklines]
   [pinkgorilla.ui.viz.vega :refer [vega vegaa]]

   [pinkgorilla.ui.viz.math]
   [pinkgorilla.ui.viz.video]
   [pinkgorilla.ui.viz.json]
   [pinkgorilla.ui.viz.frisk]

   ; control
   [pinkgorilla.ui.control.button :refer [button]]
   [pinkgorilla.ui.control.checkbox :refer [checkbox]]
   [pinkgorilla.ui.control.input :refer [input]]
   [pinkgorilla.ui.control.select :refer [select-nav]]
   [pinkgorilla.ui.control.slider]
   [pinkgorilla.ui.control.ionslider]
   [pinkgorilla.ui.control.progressbar]


   ; layout


   [pinkgorilla.ui.layout.sidebar]
   [pinkgorilla.ui.layout.gridlayout]
   [pinkgorilla.ui.layout.panel]

   [pinkgorilla.ui.layout.tailsui]

    ; app
   [pinkgorilla.ui.app.card]
   [pinkgorilla.ui.app.popover]
   [pinkgorilla.ui.app.description-list]
   [pinkgorilla.ui.app.aspect-ratio]

   ; pinkie
   [pinkgorilla.ui.pinkie.components-ui]
   [pinkgorilla.ui.pinkie.pydoc]))

(register-component :p/aggrid aggrid-styled)

; controls
(register-component :p/button button)

(register-component :p/checkbox checkbox)
(def checkbox-a (bind checkbox))
(register-component :p/checkbox-a checkbox-a)

(register-component :p/input input)
(def input-a (bind input))
(register-component :p/input-a input-a)

(register-component :p/select select-nav)
(def select-a (bind select-nav))
(register-component :p/select-a select-a)

; vega

(register-component :p/vega vega)
(register-component :p/vegaa vegaa)