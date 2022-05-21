(ns demo.notebook.pprint
  (:require
   [cljs.pprint :refer [print-table]]))

(cljs.pprint/print-table [{:name "Harry" :age "?"}
                          {:name "Dumbeldor" :age "old"}])