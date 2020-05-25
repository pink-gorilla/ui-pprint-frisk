(ns demo.app
  (:require
   [reagent.core :as r]
   [demo.example :refer [examples show-example]]))

(defn welcome []
  [:div
   [:h1 {:class "py-2"} "Gorilla UI Demo"]
   [:p {:class "py-2"} "check out the cool demos..."]])


(defn example-menu [{:keys [name goto-page] :as example}]
  [:a {:href "#"
       :on-click #(goto-page example)
       :class "block text-left xl:flex xl:items-center shadow xl:shadow-none py-3 px-3 xl:px-4 border-l-4 border-transparent text-red hover:text-blue-300 hover:bg-green-300 text-xs"}
   name])

(defn sidebar [{:keys [examples goto-page]}]
  [:div {:class "bg-orange-300 h-full w-1/12 min-h-screen xl:py-2"}
   [:div {:class "xl:block uppercase font-bold text-grey-darker text-xs px-4 py-2"}

    (map-indexed
     (fn [i example]
       ^{:key i}
       [example-menu (assoc example :goto-page goto-page)])
     examples)]])

#_(def examples (r/atom
                 [{:name "a" :component [:h1 "a"]}
                  {:name "b" :component [:h1 "b"]}]))


(defn app []
  (let [current (r/atom welcome)
        goto-page (fn [{:keys [name component] :as example}]
                    (println "showing example " name)
                    (let [c (partial show-example example)]
                      (reset! current c)))]
    (fn []
      [:<>
       [:link {:rel "stylesheet" :href "tailwindcss/dist/tailwind.css"}]
       [:div {:class "flex font-sans antialiased h-screen"}
        [sidebar {:examples @examples :goto-page goto-page}]
        [:div {:class "bg-white h-full pt-8"}
         [:div {:class "text-center w-full text-grey-darkest"}
          [@current]]]]])))