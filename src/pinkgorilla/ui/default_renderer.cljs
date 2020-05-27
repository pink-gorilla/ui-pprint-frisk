(ns pinkgorilla.ui.default-renderer
  (:require
   [pinkgorilla.ui.default-setup]
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
   [pinkgorilla.ui.gridlayout]
   ; controls
   [pinkgorilla.controls.select]
   [pinkgorilla.controls.slider]
   [pinkgorilla.controls.tailsui]))