(ns pinkgorilla.ui.viz.ansi
  (:require
   [lambdaisland.ansi :as ansi]))

(defn ^{:category :ui}
  ansi
  "renders data formatted as json 
   (helpful to export data or debugging)"
  [text]
  (let [h (ansi/text->hiccup text)]
    (println "hiccup: " h)
    (into [:div h] h)))



