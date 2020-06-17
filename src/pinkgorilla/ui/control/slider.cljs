(ns pinkgorilla.ui.control.slider
  (:require
   [reagent.core :as r]
   [pinkie.pinkie :refer-macros [register-component]]))

; dash: https://www.npmjs.com/package/rc-slider

(defn svg1 []
  [:svg {:class "absolute text-black w-full h-2 left-0 top-100"
         :x "0px"
         :y "0px"
         :viewBox "0 0 255 255"
         :space "preserve"}
   [:polygon {:class "fill-current"
              :points "0,0 127.5,127.5 255,0"}]])

(defn on-select []
  (println "on-select")
  false)

(defn ^{:category :control}
  slider []
  [:<>
   [:div {:class "flex w-64 m-auto items-center h-32 justify-center"}
    [:div {:class "py-1 relative min-w-full"}
     [:div {:class "h-2 bg-gray-200 rounded-full"}
      [:div {:class "absolute h-2 rounded-full bg-teal-600 w-0"
             :style {:width "58.5714%"}}]

      [:div {:class "absolute h-4 flex items-center justify-center w-4 rounded-full bg-white shadow border border-gray-300 -ml-2 top-0 cursor-pointer"
             :unselectable "on"
            ; :onselectstart on-select ; "return false;"
             :style {:left "58.5714%"}}
       [:div {:class "relative -mt-2 w-1"}
        [:div {:class "absolute z-40 opacity-100 bottom-100 mb-2 left-0 min-w-full"
               :style {:margin-left "-20.5px"}}
         [:div {:class "relative shadow-md"}
          [:div {:class "bg-black -mt-8 text-white truncate text-xs rounded py-1 px-4"}
           "92"]
          [svg1]]]]]
      [:div {:class "absolute text-gray-800 -ml-1 bottom-0 left-0 -mb-6"} "10"]
      [:div {:class "absolute text-gray-800 -mr-1 bottom-0 right-0 -mb-6"} "150"]]]]])

(defn ^{:category :control}
  slider-between []
  [:<>
   ; between two numbers
   [:div {:class "flex w-64 m-auto items-center h-32 justify-center"}
    [:div {:class "py-1 relative min-w-full"}
     [:div {:class "h-2 bg-gray-200 rounded-full"}
      [:div {:class "absolute h-2 rounded-full bg-teal-600 w-0"
             ;:style "width: 24.1935%; left: 11.2903%;"
             }]
      [:div {:class "absolute h-4 flex items-center justify-center w-4 rounded-full bg-white shadow border border-gray-300 -ml-2 top-0 cursor-pointer"
             ;:unselectable "on"
             ;:onselectstart "return false;"
             ;:style "left: 11.2903%;"
             }
       [:div {:class "relative -mt-2 w-1"}
        [:div {:class "absolute z-40 opacity-100 bottom-100 mb-2 left-0 min-w-full"
               ;:style "margin-left: -25px;"
               }
         [:div {:class "relative shadow-md"}
          [:div {:class "bg-black -mt-8 text-white truncate text-xs rounded py-1 px-4"} "$ 15"]
          [:svg {:class "absolute text-black w-full h-2 left-0 top-100"
                 :x "0px"
                 :y "0px"
                 :viewBox "0 0 255 255"
                 :space "preserve"}
           [:polygon {:class "fill-current"
                      :points "0,0 127.5,127.5 255,0"}]]]]]]
      [:div {:class "absolute h-4 flex items-center justify-center w-4 rounded-full bg-white shadow border border-gray-300 -ml-2 top-0 cursor-pointer"
             ;:unselectable "on"
             ;:onselectstart "return false;"
             ;:style "left: 35.4839%;"
             }
       [:div {:class "relative -mt-2 w-1"}
        [:div {:class "absolute z-40 opacity-100 bottom-100 mb-2 left-0 min-w-full"
               ;:style "margin-left: -25px;"
               }
         [:div {:class "relative shadow-md"}
          [:div {:class "bg-black -mt-8 text-white truncate text-xs rounded py-1 px-4"} "$ 30"]
          [:svg {:class "absolute text-black w-full h-2 left-0 top-100"
                 :x "0px"
                 :y "0px"
                 :viewBox "0 0 255 255"
                 :space "preserve"}
           [:polygon {:class "fill-current"
                      :points "0,0 127.5,127.5 255,0"}]]]]]]
      [:div {:class "absolute text-gray-800 -ml-1 bottom-0 left-0 -mb-6"} "$ 8"]
      [:div {:class "absolute text-gray-800 -mr-1 bottom-0 right-0 -mb-6"} "$ 70"]]]]])

(register-component :p/slider slider)

(println ":p/slider was registered!")