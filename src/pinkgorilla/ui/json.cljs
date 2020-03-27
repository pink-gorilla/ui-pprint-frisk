(ns pinkgorilla.ui.json
  "custom components need to define a javascript-module
   module-test is a very simple sample that just renders the data as
   JSON to the dom node that is passed.
   In early state component development this is helpful."
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]]))

(defn json [data]
  [jsrender {:module "pinkie-json" :data data}])

(register-tag :json json)

