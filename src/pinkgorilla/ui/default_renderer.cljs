(ns pinkgorilla.ui.default-renderer
  (:require
   ; parent project setups
   [pinkie.default-setup]

   [pinkgorilla.ui.css]

   ; control
   [pinkgorilla.ui.control.button]
   [pinkgorilla.ui.control.checkbox]
   [pinkgorilla.ui.control.input]
   [pinkgorilla.ui.control.ionslider]
   [pinkgorilla.ui.control.progressbar]
   [pinkgorilla.ui.control.select]
   [pinkgorilla.ui.control.slider]

   ; data
   [pinkgorilla.ui.data.aggrid]
   [pinkgorilla.ui.data.cytoscape]
   [pinkgorilla.ui.data.highchart]
   [pinkgorilla.ui.data.leaflet]
   [pinkgorilla.ui.data.sparklines]
   [pinkgorilla.ui.data.vega]

   ; layout
   [pinkgorilla.ui.layout.sidebar]
   [pinkgorilla.ui.layout.aspect-ratio]
   [pinkgorilla.ui.layout.card]
   [pinkgorilla.ui.layout.gridlayout]
   [pinkgorilla.ui.layout.panel]
   [pinkgorilla.ui.layout.tailsui]

    ; ui
   [pinkgorilla.ui.ui.json]
   [pinkgorilla.ui.ui.markdown]
   [pinkgorilla.ui.ui.math]
   [pinkgorilla.ui.ui.player]
   [pinkgorilla.ui.ui.popover]

   ; pinkie
   [pinkgorilla.ui.pinkie.components-ui]
   [pinkgorilla.ui.pinkie.frisk]
   [pinkgorilla.ui.pinkie.pydoc]
   [pinkgorilla.ui.pinkie.clock] ; has no dependencies
   ))