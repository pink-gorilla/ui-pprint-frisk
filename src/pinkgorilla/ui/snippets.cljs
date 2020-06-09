(ns pinkgorilla.ui.snippets
  (:require
   [pinkgorilla.ui.pinkie :refer-macros [register-component]]
   [pinkgorilla.ui.error :refer [error-boundary]]
   [pinkgorilla.ui.pinkie-render :refer [reagent-inject]]))

(defn console-cell [c]
  [:div.bg-gray-700.gray-100.font-mono.w-full
   [:textarea.bg-gray-700 {:defaultValue c
                           ;:value c
                           }]])

(defn code-cell [c]
  [:div.w-full.bg-blue-200.font-mono
   [:p c]])

(defn pinkie-cell [p]
  [:div.w-full.mt-5
   [error-boundary
    [reagent-inject {:map-keywords true} p]]])

(defn snippet [{:keys [code pinkie out]}]
  [:div.w-full.mt-5.p-2
   (when code
     [code-cell code])
   (when out
     [console-cell out])
   (when pinkie
     [pinkie-cell pinkie])])

(defn ^{:category :pinkie}
  snippets
  "renders a snippet list"
  [list]
  [:div.w-full.h-full
   (into [:div] (map snippet list))])

(register-component :p/snippets snippets)