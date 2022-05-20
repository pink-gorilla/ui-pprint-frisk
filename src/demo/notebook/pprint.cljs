(ns demo.notebook.pprint
  [cljs.pprint :refer [print-table]])

(print-table {:name "Harry" :age "?"
              :name "Dumbeldor" :age "old"})