(ns pinkgorilla.ui.demo.core
  (:require
   [cljs.pprint]
   [reagent.core :as r]
   [reagent.dom]
   [pinkgorilla.ui.pinkie :refer [tag-inject renderer-list]]
   ; add dependencies of this project to bundle
   [pinkgorilla.ui.default-renderer]

   ; DEMOS
   ; from renderable-ui
   [pinkgorilla.ui.demo.gtable]
   ; js based enderer
   [pinkgorilla.ui.demo.highchart]
   [pinkgorilla.ui.demo.json]
   [pinkgorilla.ui.demo.vega]
   [pinkgorilla.ui.demo.math]
   ; reagent based renderer
   [pinkgorilla.ui.demo.leaflet]
   [pinkgorilla.ui.demo.sparklines]
   [pinkgorilla.ui.demo.player]
   [pinkgorilla.ui.demo.aggrid]
   [pinkgorilla.ui.demo.clock]))

(defn print-registered-tags []
  (with-out-str
    (cljs.pprint/print-table (renderer-list))))

(defn example [component tagline]
  [:div {:style {:background-color "yellow"}}
   [:p "example: " [:b tagline]]
   component])


(def frisk-demo
  [:div
   [:p/frisk {:a "I'm a string"
              :b :imakeyword
              :c [1 2 3]
              :d '(1 2 3)
              :e #{1 2 3}
              :f (clj->js {:i-am "an-object"})
              "g" "String key"
              0 nil
              "not a number" js/NaN}]
   [:p "Frisk Demo end"]])

(def pydoc-demo
  [:div
   [:p/pydoc
    {"transpose" 
     {:module "numpy"
      :name "transpose"
      :type :function
      :flags #{:fn? :callable?}
      :args []
      :varkw "kwargs"
      :varargs "args"
      :kwonlyargs []
      :arglists "[[& [args {:as kwargs}]]]"
      :kwonlydefaults nil
      :defaults nil
      :str "<function transpose at 0x7f939c0f84d0>"
      :annotations {}
      :doc "\n    Permute the dimensions of an array.\n\n    Parameters\n    ----------\n    a : array_like\n        Input array.\n    axes : list of ints, optional\n        By default, reverse the dimensions, otherwise permute the axes\n        according to the values given.\n\n    Returns\n    -------\n    p : ndarray\n        `a` with its axes permuted.  A view is returned whenever\n        possible.\n\n    See Also\n    --------\n    moveaxis\n    argsort\n\n    Notes\n    -----\n    Use `transpose(a, argsort(axes))` to invert the transposition of tensors\n    when using the `axes` keyword argument.\n\n    Transposing a 1-D array returns an unchanged view of the original array.\n\n    Examples\n    --------\n    >>> x = np.arange(4).reshape((2,2))\n    >>> x\n    array([[0, 1],\n           [2, 3]])\n\n    >>> np.transpose(x)\n    array([[0, 2],\n           [1, 3]])\n\n    >>> x = np.ones((1, 2, 3))\n    >>> np.transpose(x, (1, 0, 2)).shape\n    (2, 1, 3)\n\n    "}}]])


(def app
  [:<>
   [:p/text (print-registered-tags)]

   [example frisk-demo "frisk"]
   [example pydoc-demo "pydoc"]
   
   
   [example pinkgorilla.ui.demo.gtable/demo "gtable"]
   [example [:div [:span 123] [:p/bongo 456] [:span 789]] "bad-renderer"]
   [example [:div [:h1 "jquery gets loaded below .. jippie "]
             [:p/phtml "<script src='https://code.jquery.com/jquery-3.4.1.min.js'>>/script>"]]]

     ; js based renderer
   [example pinkgorilla.ui.demo.math/demo "js-mathjax"]
   [example pinkgorilla.ui.demo.vega/demo "js-vega"]
   [example pinkgorilla.ui.demo.json/demo "js-json"]
   [example pinkgorilla.ui.demo.highchart/demo "js-highchart"]
     ;reagent based renderer
   [example pinkgorilla.ui.demo.leaflet/demo "leaflet-map"]
   [example pinkgorilla.ui.demo.sparklines/demo "sparklines"]
   [example pinkgorilla.ui.demo.player/demo "player"]
   [example pinkgorilla.ui.demo.aggrid/demo "ag-grid"]
   [example pinkgorilla.ui.demo.clock/demo "clock"]])


(defn stop []
  (js/console.log "Stopping..."))

(defn start []
  (js/console.log "Starting...")
  ;(js/console.log (print-registered-tags))
  (reagent.dom/render (tag-inject app)
                      (.getElementById js/document "app")))

(defn ^:export init []
  (start))
