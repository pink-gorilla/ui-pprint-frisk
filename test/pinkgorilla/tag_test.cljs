(ns pinkgorilla.tag-test
  (:require
   [cljs.test :refer-macros [deftest is]]
   [pinkgorilla.ui.pinkie :refer [tag-inject]]
   [pinkgorilla.ui.math :refer [math]]))

(deftest tag-replacer-map-is-no-vector  []
  (is (= (vector? [:math "math"]) true))
  (is (= (vector? {:math "math"}) false)))

;; test if the keyword :math gets replaced with math function

(deftest tag-replacer-test  []
  (let [math-markup "(ax ^2 + bx + c = 0 ) a"]
    (is (= (tag-inject [:math math-markup]) [math math-markup])) ;; exchange tags in a vector
    (is (= (tag-inject {:math math-markup}) {:math math-markup})))) ;; tags in a map should NOT be exchanged


