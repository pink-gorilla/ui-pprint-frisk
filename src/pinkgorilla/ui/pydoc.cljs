(ns pinkgorilla.ui.pydoc
  (:require
   [clojure.string :as str]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

;{:module "numpy"
; :name "transpose"
; :type :function, 
; :flags #{:fn? :callable?},
; :args [], 
; :varkw "kwargs", 
; :varargs "args", 
; :kwonlyargs [],
; :arglists [[& [args {:as kwargs}]]], 
; :kwonlydefaults nil, 
; :defaults nil, 
; :str "<function transpose at 0x7f939c0f84d0>", 
; :annotations {}, 
; :doc "\n    Permute the dimensions of an array.\n\n    Parameters\n    ----------\n    a : array_like\n        Input array.\n    axes : list of ints, optional\n        By default, reverse the dimensions, otherwise permute the axes\n        according to the values given.\n\n    Returns\n    -------\n    p : ndarray\n        `a` with its axes permuted.  A view is returned whenever\n        possible.\n\n    See Also\n    --------\n    moveaxis\n    argsort\n\n    Notes\n    -----\n    Use `transpose(a, argsort(axes))` to invert the transposition of tensors\n    when using the `axes` keyword argument.\n\n    Transposing a 1-D array returns an unchanged view of the original array.\n\n    Examples\n    --------\n    >>> x = np.arange(4).reshape((2,2))\n    >>> x\n    array([[0, 1],\n           [2, 3]])\n\n    >>> np.transpose(x)\n    array([[0, 2],\n           [1, 3]])\n\n    >>> x = np.ones((1, 2, 3))\n    >>> np.transpose(x, (1, 0, 2)).shape\n    (2, 1, 3)\n\n    "
; }

(defn py-entry [f]
  (let [name (:name f)
        name (if (str/blank? name) "<no-name>" name)]
    [:span.mx-1.bg-orange-300.tooltip name
     [:span.tooltip-text.bg-blue-200 {:class ".w-6/12"} (:doc f)]]))

(defn py-category [category-name items]
  [:<>
   [:p (str category-name " (" (count items) ") : ")]
   (doall (map-indexed
           (fn [i data] ^{:key i} [py-entry data]) items))])

(defn of-type [type items]
  (filter #(= type (:type %)) items))

(defn py-doc [py-module]
  (let [items (if (map? py-module)
                (vals py-module)
                py-module)

        f-items (of-type :function items)
        t-items (of-type :type  items)

        int-items (of-type :int items)
        float-items (of-type :float items)
        str-items (of-type :str items)

        ufunc-items (of-type :ufunc items)
        module-items (of-type :module items)

        builtin-items (of-type :builtin-function-or-method items)

        ; :pytest-tester

        ;_ (println "function count: " (count f-items))
        ]
    [:<>
     [:link {:rel "stylesheet" :href "pydoc.css"}]
     [py-category "Functions" f-items]

     [py-category "Types" t-items]
     [py-category "UFunc" ufunc-items]
     [py-category "Module" module-items]
     [py-category "BuiltIn" builtin-items]

     [py-category "Int" int-items]
     [py-category "Float" float-items]
     [py-category "Str" str-items]]))

(register-tag :p/pydoc py-doc)

