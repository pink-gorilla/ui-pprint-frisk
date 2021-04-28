(ns pinkgorilla.ui.app.description-list
  (:require
   [pinkie.pinkie :refer-macros [register-component]]))

; https://tailwindui.com/preview
; 

(defn item [k v]
  ; odd: bg-gray-50
  ; even: bg-white
  [:div {:class "bg-gray-50 px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"}
   [:dt {:class "text-sm font-medium text-gray-500"} k]
   [:dd {:class "mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2"} v]])

(defn attachment [n]
  [:li {:class "pl-3 pr-4 py-3 flex items-center justify-between text-sm"}
   [:div {:class "w-0 flex-1 flex items-center"}
       ; "<!-- Heroicon name: solid/paper-clip -->"
    [:svg {:class "flex-shrink-0 h-5 w-5 text-gray-400"
           :xmlns "http://www.w3.org/2000/svg"
           :view-box "0 0 20 20", :fill "currentColor", :aria-hidden "true"}
     [:path {:fill-rule "evenodd", :d "M8 4a3 3 0 00-3 3v4a5 5 0 0010 0V7a1 1 0 112 0v4a7 7 0 11-14 0V7a5 5 0 0110 0v4a3 3 0 11-6 0V7a1 1 0 012 0v4a1 1 0 102 0V7a3 3 0 00-3-3z", :clip-rule "evenodd"}]]
    [:span {:class "ml-2 flex-1 w-0 truncate"} n]]
   [:div {:class "ml-4 flex-shrink-0"}
    [:a {:href "#", :class "font-medium text-indigo-600 hover:text-indigo-500"}]]])

(defn description-list [title subtitle data cols]
  [:div {:class "bg-white shadow overflow-hidden sm:rounded-lg"}
   [:div {:class "px-4 py-5 sm:px-6"}
    [:h3 {:class "text-lg leading-6 font-medium text-gray-900"} title]
    [:p {:class "mt-1 max-w-2xl text-sm text-gray-500"} subtitle]]
   [:div {:class "border-t border-gray-200"}
    [:dl
     (into [:dl]
           (map (fn [[k v]]
                  [item (k cols) v]) data))

     [:div {:class "bg-white px-4 py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6"}
      [:dt {:class "text-sm font-medium text-gray-500"}]
      [:dd {:class "mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2"}
       [:ul {:class "border border-gray-200 rounded-md divide-y divide-gray-200"}
        [attachment "resume_back_end_developer.pdf"]
        [attachment "coverletter_back_end_developer.pdf"]]]]]]])

(register-component :p/description-list description-list)
