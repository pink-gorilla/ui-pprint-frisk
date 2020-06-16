(ns demo.views
  (:require
   [reagent.core :as r]
   [demo.example :refer [examples show-example]]))

(defn welcome []
  [:section {:class "text-gray-700 body-font"}
   [:div {:class "container px-5 py-24 mx-auto"}
    [:div {:class "xl:w-1/2 lg:w-3/4 w-full mx-auto text-center"}
     [:svg {:xmlns "http://www.w3.org/2000/svg"
            :fill "currentColor" 
            :class "inline-block w-8 h-8 text-gray-400 mb-8"
            :viewBox "0 0 975.036 975.036"}
      [:path {:d "M925.036 57.197h-304c-27.6 0-50 22.4-50 50v304c0 27.601 22.4 50 50 50h145.5c-1.9 79.601-20.4 143.3-55.4 191.2-27.6 37.8-69.399 69.1-125.3 93.8-25.7 11.3-36.8 41.7-24.8 67.101l36 76c11.6 24.399 40.3 35.1 65.1 24.399 66.2-28.6 122.101-64.8 167.7-108.8 55.601-53.7 93.7-114.3 114.3-181.9 20.601-67.6 30.9-159.8 30.9-276.8v-239c0-27.599-22.401-50-50-50zM106.036 913.497c65.4-28.5 121-64.699 166.9-108.6 56.1-53.7 94.4-114.1 115-181.2 20.6-67.1 30.899-159.6 30.899-277.5v-239c0-27.6-22.399-50-50-50h-304c-27.6 0-50 22.4-50 50v304c0 27.601 22.4 50 50 50h145.5c-1.9 79.601-20.4 143.3-55.4 191.2-27.6 37.8-69.4 69.1-125.3 93.8-25.7 11.3-36.8 41.7-24.8 67.101l35.9 75.8c11.601 24.399 40.501 35.2 65.301 24.399z"}]]
     [:p {:class "leading-relaxed text-lg"} 
      "enjoy the demos ..."]
     [:span {:class "inline-block h-1 w-10 rounded bg-indigo-500 mt-8 mb-6"}]
     [:h2 {:class "text-gray-900 font-medium title-font tracking-wider text-sm"}
      "gorilla"]
     [:p {:class "text-gray-500"} 
      "UI"]]]]
  )


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
       [:div.flex.font-sans.antialiased.h-screen
        [sidebar {:examples @examples :goto-page goto-page}]
        [:div.bg-white.h-full.w-full.text-center.text-grey-darkest
          [@current]]]])))