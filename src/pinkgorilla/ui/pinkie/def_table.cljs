(ns comp.def-table
  (:require [cljs.spec.alpha :as s]
            [reagent.core :as reagent]))

;; from: https://github.com/kishanov/reagent-definition-table/blob/master/src/def_table/core.cljs
;; https://medium.com/@kirill.ishanov/reagent-component-from-scratch-definition-table-5ad1d8825b87

(s/def ::path-to-value (s/coll-of keyword :kind vector))
(s/def ::label string?)
(s/def ::formatter fn?)
(s/def ::row-def (s/tuple ::path-to-value ::label ::formatter))
(s/def ::rows-def (s/coll-of ::row-def :min-count 1 :distinct true))

(defn definition-table [obj rows-def & [options]]
  {:pre [(if (s/valid? ::rows-def rows-def)
           true
           (do
             (js/console.error (s/explain-str ::rows-def rows-def))
             false))]}

  [:table.ui.definition.table
   {:class (:table-css-class options)}
   (into [:tbody]
         (map (fn [[path-to-value label formatter]]
                [:tr
                 [:td
                  {:class (:left-column-css-class options)}
                  [:div
                   {:class (when (:ribbon-label? options)
                             "ui ribbon label")}
                   label]]
                 [:td
                  {:class (:right-column-css-class options)}
                  (formatter (get-in obj path-to-value))]])
              rows-def))])

(defn compact-definition-steps [obj secitons & [options]]
  (let [active-step (reagent/atom 0)]
    (fn [obj sections]
      [:div.ui.grid
       [:div.two.column.row
        [:div.column
         {:class (or (:left-column-width options) "six wide")}
         (->> sections
              (map-indexed vector)
              (map (fn [[i [title]]]
                     [:a.step
                      {:class    (when (= @active-step i) "active")
                       :on-click (fn [e]
                                   (.preventDefault e)
                                   (reset! active-step i))}
                      [:div.content
                       [:div.title title]]]))
              (into [:div.ui.vertical.steps]))]

        [:div.column
         {:class (:right-column-width options)}
         (let [[_ rows-def def-table-options] (get (vec sections) @active-step)]
           ^{:key @active-step}
           [definition-table
            obj
            rows-def
            def-table-options])]]])))

(defn expanded-definition-steps [obj steps-def & [options]]
  (let [sections (filter first steps-def)
        headers (map (fn [[title]]
                       [:div.ui.row
                        [:div.column
                         [:h4.ui.dividing.header title]]])
                     sections)
        contents (->> (range (count sections))
                      (map (fn [i]
                             [:div.ui.centered.row
                              [:div.twelve.wide.column.center.aligned
                               (let [[_ rows-def def-table-options] (get (vec sections) i)]
                                 [definition-table
                                  obj
                                  rows-def
                                  def-table-options])]])))]

    (->> (interleave headers contents)
         (into [:div.ui.grid]))))

(defn definition-sections [obj sections-def & [options]]
  (let [view-type (reagent/atom ::expanded)]
    (fn [obj sections-def]
      [:div.ui.grid
       [:div.one.column.row
        [:div.column
         [:div.ui.compact.tiny.menu
          [:a.item
           {:class    (when (= @view-type ::expanded) "active")
            :on-click #(reset! view-type ::expanded)}
           "Expanded View"]
          [:a.item
           {:class    (when (= @view-type ::compact) "active")
            :on-click #(reset! view-type ::compact)}
           "Compact View"]]]]
       [:div.one.column.row
        [:div.column
         ^{:key @view-type}
         [(case @view-type
            ::expanded expanded-definition-steps
            ::compact compact-definition-steps)
          obj (filter first sections-def) options]]]])))
