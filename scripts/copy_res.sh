#!/bin/bash

cpr () {
  sourcedir="node_modules/$1"
  source="$sourcedir/$2"
  targetdir="target/node_modules/public/$1" 
  if [ -d $sourcedir ]; then
     echo "copying $source ==> $targetdir"
     mkdir -p $targetdir
     cp $source $targetdir
  else 
    echo "ERROR: $sourcedir does not exist."
  fi
}

# already done in webly:
#cpr "@fortawesome/fontawesome-free/webfonts" "*.*"

cpr "leaflet/dist" "*.css"
cpr "ag-grid-community/dist/styles" "*.css"
cpr "react-grid-layout/css" "*.css"
cpr "react-resizable/css" "*.css"

# this is done in notebook-ui:
# cpr "highlight.js/styles" "*.css"
