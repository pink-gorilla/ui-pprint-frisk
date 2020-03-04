(ns pinkgorilla.ui.json
  "custom components need to define a javascript-module
   module-test is a very simple sample that just renders the data as
   JSON to the dom node that is passed.
   In early state component development this is helpful."
  (:require
   [pinkgorilla.ui.pinkie :refer [register-tag]]
   [pinkgorilla.ui.jsrender :refer [jsrender]])
  (:require-macros [pinkgorilla.ui.macros :refer [inline-resource]]))

#_(def module "
  define([], function () {
      return {
         version: 'module-test 0.0.3',
         render: function (id_or_domel, data) {
            var domElement = id_or_domel;
            if (typeof id_or_domel === 'string' || id_or_domel instanceof String) {
               selector = '#'+ id_or_domel;
               console.log ('module-json is rendering to selector: ' + selector);
               domElement = document.getElementById (selector); 
            } else {
               console.log ('module-json is rendering to dom-element');
            }

            var dataJson = JSON.stringify(data)
            console.log ('module-json data: ' + dataJson);

            var p = document.createElement ('p');
            var json = JSON.stringify (data);
            var textnode = document.createTextNode (dataJson);  
            //var textnode = document.createTextNode ('module-test rocks');  
            p.appendChild (textnode);
            domElement.appendChild (p);
         }
      }
  });
")

(def module (inline-resource "pinkgorilla/ui/json-module.js"))

(defn json [data]
  [jsrender {:module module :data data}])

(register-tag :json json)

