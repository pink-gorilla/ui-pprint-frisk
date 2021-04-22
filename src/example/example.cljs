(ns example.example
  (:require
   [fipp.clojure]
   [pinkie.text :refer [text]]
   [pinkie.error :refer [error-boundary]]
   [pinkie.pinkie :refer [tag-inject]]))

(defonce examples (atom {}))

(defn- add-example [acc example]
  ;(println "adding: " example)
  (let [{:keys [name component]} example
        v (or (get acc name) [])]
    (assoc acc name (conj v component))))

(defn add [name component]
  (swap! examples add-example {:name name
                               :ns (namespace name)
                               :component component}))

(defn pr-str-nice [config]
  (let [spec (with-out-str
               (fipp.clojure/pprint config {:width 150}))]
    ;(println "spec: " spec)
    spec))

(defn example [component]
  [:div
   [error-boundary
    (tag-inject component)]
   [:div.bg-gray-300.m-5.text-blue-400
    [text (pr-str-nice component)]]])

(defn example-page [name components]
  ;[:div.flex.flex-col.w-full.h-full ; {:style {:background-color "yellow"}}
   ;[:h1.mb-5 name]
  [:div.w-full.h-screen
   [:h1 "Example: " name]
   (into [:<>] (map example components))])

(defn ns-comp [c]
  (println "checking: " (:name c))
  (let [n (namespace (:name c))]

    n))

(defn examples-wrapped [nsf]
  (let [all (map (fn [[name-kw components]]
                   ;(let [name (if (keyword? name-kw) (str name-kw) name-kw)]
                   {:name name-kw
                    :page (example-page name components)})
                 @examples)
        visible (if nsf
                  (filter #(= nsf (ns-comp %)) all)
                  all)]
    visible))

