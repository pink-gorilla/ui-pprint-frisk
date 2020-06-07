(ns pinkgorilla.ui.default-renderer
  (:require
   [pinkgorilla.ui.default-setup]
   [pinkgorilla.ui.components-ui]
   ; controls
   [pinkgorilla.controls.button]
   [pinkgorilla.controls.input]
   [pinkgorilla.controls.select]
   [pinkgorilla.controls.slider]
   [pinkgorilla.controls.ionslider]
   [pinkgorilla.controls.popover]
   [pinkgorilla.controls.progressbar]
   [pinkgorilla.controls.tailsui]
   [pinkgorilla.controls.markdown]
   [pinkgorilla.controls.checkbox]
   [pinkgorilla.controls.panel]
   [pinkgorilla.controls.aspect-ratio]
   ; js
   [pinkgorilla.ui.json]
   [pinkgorilla.ui.highchart]
   [pinkgorilla.ui.math]
   [pinkgorilla.ui.vega]
   ; reagent
   [pinkgorilla.ui.aggrid]
   [pinkgorilla.ui.leaflet]
   [pinkgorilla.ui.player]
   [pinkgorilla.ui.sparklines]
   [pinkgorilla.ui.clock] ; has no dependencies
   [pinkgorilla.ui.frisk]
   [pinkgorilla.ui.pydoc]
   [pinkgorilla.ui.gridlayout]))