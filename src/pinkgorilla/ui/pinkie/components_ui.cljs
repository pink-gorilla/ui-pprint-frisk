(ns pinkgorilla.ui.pinkie.components-ui
  (:require
   [clojure.string :as str]
   [reagent.core :as r]
   [pinkie.text :refer [text]]
   [pinkie.pinkie :refer [component-list] :refer-macros [register-component]]))

(defn docstring [c]
  (get-in c [:meta :doc]))

(defn comp-entry [show-comp c]
  (let [name (:tag c)
        docstring? (not (str/blank? (docstring c)))
        ;doc (or (get-in c [:meta :doc]) "No docstring!")
        ]
    [:span.m-2.p-2
     {:class (if docstring? " bg-blue-300" " bg-orange-300")
      :on-mouse-over #(show-comp c)}
     name]))

(defn comp-category [show-comp [category-name components]]
  [:div.w-full.align-left.pt-2
   [:div.text-2xl.tracking-wide.m-2.border-b-2.pb-1
    (str category-name)]
   [:div
    (doall (map-indexed
            (fn [i c] ^{:key i}
              [comp-entry show-comp c])
            components))]])

(defn category [component]
  (get-in component [:meta :category]))

(defn comp-doc [c]
  [:div
   [:p (name (:tag c))]
   [text (docstring c)]])

(defn ^{:category :pinkie}
  components-ui
  "displays a list of currently registered  pinkie components."
  []
  (let [components (component-list)
        groups (group-by category components)
        _ (println "category count: " (count groups))
        selected-comp (r/atom nil)
        show-comp (fn [c] (println "hover: " (:tag c))
                    (reset! selected-comp c))]
    (fn []
      [:div.flex.flex-col.w-full.h-full
       (into [:div.h-full.w-full] (map (partial comp-category show-comp) groups))
       [:div.h-64.w-full.p-2.bg-gray-400.border-solid.border-blue-300..overflow-scroll
        (if-let [c @selected-comp]
          [comp-doc c]
          [:p "Hover on components.."])]])))

(register-component :p/componentsui components-ui)

