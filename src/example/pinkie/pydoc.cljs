(ns example.pinkie.pydoc
  (:require
   [example.example :as example]))

(example/add
 :pinkie/pydoc
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


