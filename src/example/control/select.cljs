(ns example.control.select
  (:require
   [reagent.core :as r]
   [example.example :as example]))

(def languages ["javascript" "ruby" "clojure" "clojurescript" "ocaml" "scheme" "elixir" "c#" "R" "python"])

(defonce state (r/atom {:language "ruby"}))

(example/add
 :control/select
 [:div
  [:h2 "select"]
  [:p/pselectm {:nav? true} languages state :language]])