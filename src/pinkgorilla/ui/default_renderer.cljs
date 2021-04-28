(ns pinkgorilla.ui.default-renderer
  (:require
   ; parent project setups
   [pinkie.default-setup]

   [pinkgorilla.ui.css]

    ; viz
   [pinkgorilla.ui.viz.aggrid]
   [pinkgorilla.ui.viz.cytoscape]
   [pinkgorilla.ui.viz.highchart]
   [pinkgorilla.ui.viz.leaflet]
   [pinkgorilla.ui.viz.sparklines]
   [pinkgorilla.ui.viz.vega]
   [pinkgorilla.ui.viz.markdown]
   [pinkgorilla.ui.viz.math]
   [pinkgorilla.ui.viz.video]
   [pinkgorilla.ui.viz.json]
   [pinkgorilla.ui.viz.frisk]

   ; control
   [pinkgorilla.ui.control.button]
   [pinkgorilla.ui.control.checkbox]
   [pinkgorilla.ui.control.input]
   [pinkgorilla.ui.control.ionslider]
   [pinkgorilla.ui.control.progressbar]
   [pinkgorilla.ui.control.select]
   [pinkgorilla.ui.control.slider]

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
   [pinkgorilla.ui.pinkie.pydoc]
   [pinkgorilla.ui.pinkie.clock] ; has no dependencies
   ))


