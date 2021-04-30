{:npm-deps
 {; shadow cljs creates package.json 
  ; based on this dependencies 

  ; fullscreen
  ;"react-full-screen" "^1.0.2"; does not work - hooks issue needs to be investigated

  ;vega
  "vega" "^5.11.1"
  "vega-embed" "^6.7.0"
  "vega-lite" "^4.11.0"

  ; highcharts
  "highcharts" "^9.0.1" ; "^8.0.4"

  ; ag grid
  "ag-grid-community" "^25.2.0"
  "ag-grid-react" "^25.2.0"


  ; leaflet maps


  "react-leaflet" "^3.1.0" ; "^2.6.0"
  "leaflet" "^1.6.0"  ; icons

  ; graphs
  "cytoscape" "^3.18.2"
  "cytoscape-cose-bilkent" "^4.1.0"
  "cytoscape-dagre" "^2.3.2"
  "react-cytoscapejs" "^1.2.1"

  ; video
  "react-player" "^2.9.0" ; "^1.14.2"

  ;sparklines
  "react-sparklines" "^1.7.0"

  ; markdown viewer
  ; "highlight.js" "^10.0.3" ; code-coloring - notebook-ui
  "marked" "^2.0.3"

  ; ui controls
  "@popperjs/core" "^2.4.0"
  "tails-ui" "^0.5.5"
  "react-ion-slider" "^1.0.5"
  "jquery" "^3.5.1" ; required by react-ion-slider as peer dependency
  "react-grid-layout" "^1.2.4" ; "^0.18.3"

  "shadow-cljs" "^2.12.5" ; buffer polyfill fix

  ; react is coming all from webly
  ;"react" "^16.13.0"
  ;"react-dom" "^16.13.0"
  ;"react-dom-factories" "^1.0.2"
  ;"create-react-class" "^15.6.3"

 ;
  }}
