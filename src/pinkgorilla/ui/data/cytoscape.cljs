(ns pinkgorilla.ui.data.cytoscape
  (:require
   [re-frame.core :as rf]
   ["cytoscape" :as c :refer [Cytoscape]]
   ["cytoscape-cose-bilkent" :as cs :refer [COSEBilkent]]
   ["react-cytoscapejs" :as CytoscapeComponent ;:refer [CytoscapeComponent]
    ]
   [pinkie.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.size :refer [size]]))

(defn ^{:category :data}
  cytoscape
  [data]
  (println "data: " data)
  (println "cc:" CytoscapeComponent)
  [:> CytoscapeComponent data])

(defn ag-theme-classname [theme]
  (if (= theme true)
    ""
    (str "ag-theme-" theme)))

(defn cytoscape-styled [data]
  (let [theme (rf/subscribe [:css/theme-component :aggrid])]
    (fn [data]
      [:div (merge
             {:className (ag-theme-classname @theme)} ;(str "ag-theme-balham"
             (size (:size data)))
       [cytoscape data]])))

(register-component :p/cytoscape cytoscape)
