(ns pinkgorilla.ui.macros
  (:require [clojure.java.io :as io]))

(defmacro inline-resource
  [resource-path]
  (if-let [res (io/resource resource-path)]
    (slurp res)
    (do
      (println (str "inline-resource " resource-path " does not exist"))
      ;(throw (Exception (str "inline-resource " resource-path " does not exist")))
      "")))

