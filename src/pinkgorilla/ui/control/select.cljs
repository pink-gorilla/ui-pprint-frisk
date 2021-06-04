(ns pinkgorilla.ui.control.select
  (:require
   [reagent.core :as r]
   [pinkgorilla.ui.control.button :refer [button]]))

(defn css []
  [:style ".top-100 {top: 100%}
           .bottom-100 {bottom: 100%}
           .max-h-select {
              max-height: 300px;
           }
          "])

(defn- select-item [{:keys [value display selected? position select]}]
  (let [text (if display (display value) value)
        s (when selected? "bg-red-500 border-teal-600")
        p (case position
            :first   "rounded-t"
            :last    "rounded-b"
            "")]
    [:div {:class (str "cursor-pointer w-full border-gray-100 border-b hover:bg-blue-300 " p)}
     [:div {:on-click #(select value)
            :class (str "flex w-full items-center p-2 pl-2 border-transparent border-l-2 relative hover:bg-teal-600 hover:text-teal-100 " s)}
      [:div {:class "w-full items-center flex"}
       [:div {:class "mx-2 leading-6"} text]]]]))

(defn- button-remove-selection [h]
  [:div
   [:button {:class "cursor-pointer w-6 h-full flex items-center text-gray-400 outline-none focus:outline-none"
             :on-click h}
    [:svg {:stroke "currentColor"
           :fill "none"
           :stroke-linejoin "round"
           :width "100%"
           :viewBox "0 0 24 24"
           :xmlns "http://www.w3.org/2000/svg"
           :stroke-linecap "round"
           :stroke-width "2"
           :class "feather feather-x w-4 h-4"
           :height "100%"}
     [:line {:x1 "18" :y1 "6" :x2 "6" :y2 "18"}]
     [:line {:x1 "6" :y1 "6" :x2 "18" :y2 "18"}]]]])

(defn- button-dropdown [dropdown? h]
  (let [points (if dropdown?
                 "18 15 12 9 6 15"
                 "18 9  12 15 6 9")]
    [:div {:class "text-gray-300 w-8 py-1 pl-2 pr-1 border-l flex items-center border-gray-200 svelte-1l8159u"}
     [:button {:class "cursor-pointer w-6 h-6 text-gray-600 outline-none focus:outline-none"
               :on-click h}
      [:svg {:stroke "currentColor"
             :fill "none"
             :stroke-linejoin "round"
             :width "100%"
             :viewBox "0 0 24 24"
             :xmlns "http://www.w3.org/2000/svg"
             :stroke-linecap "round"
             :stroke-width "2"
             :class "feather feather-chevron-up w-4 h-4"
             :height "100%"}
       [:polyline {:points points}]]]]))

(defn ^{:category :control}
  select [{:keys [items value on-change]}]
  (let [l (count items)
        l1 (- l 1)
        dropdown? (r/atom false)
        toggle-dropdown #(swap! dropdown? not)
        pos-key (fn [i]
                  (case i
                    0 :first
                    l1 :last
                    nil))
        select #(do (reset! dropdown? false)
                    (when on-change (on-change %)))
        unselect #(when on-change (on-change nil))
        no-op #()]
    (fn [{:keys [items value display on-change]}]
      [:<>
       [css]
       ;[:div {:class "flex-auto flex flex-col items-center"} ; h-64
       [:div {:class "flex flex-col items-center relative"}

        [:div {:class "w-full svelte-1l8159u"}
         [:div {:class "my-2 bg-white p-1 flex border border-gray-200 rounded svelte-1l8159u"}
          [:div {:class "flex flex-auto flex-wrap"}]
          [:input {:value (if display (display value) value)
                   :on-change no-op
                   :class "p-1 px-2 appearance-none outline-none w-full text-gray-800 svelte-1l8159u"}]
          [button-remove-selection unselect]
          [button-dropdown @dropdown? toggle-dropdown]]]

        (when @dropdown?
          [:div {:class (str "absolute bg-blue-200 shadow z-50 w-full lef-0 rounded max-h-select top-100 " ; top-100
                             "overflow-y-auto")}
           [:div {:class "flex flex-col w-full"}
            (doall (map-indexed (fn [i v]
                                  ^{:key i}
                                  [select-item
                                   {:value v
                                    :display display
                                    :selected? (= value v)
                                    :position (pos-key i)
                                    :select select}])
                                items))]])]
;        ]
       ])))

(defn go-next [v list action]
  (let [new-index (inc (.indexOf list v))
        new-index (if (= new-index (count list)) 0 new-index)
        new-value (nth list new-index)]
    (action new-value)))

(defn go-prior [v list action]
  (let [new-index (dec (.indexOf list v))
        new-index (if (< new-index 0) (- (count list) 1) new-index)
        new-value (nth list new-index)]
    (action new-value)))

(defn ^{:category :control}
  select-nav [{:keys [items value on-change nav?] :as opts}]
  (if nav?
    [:<>
     [select opts]
     [button {:on-click #(go-prior value items on-change)} "<"]
     [button {:on-click #(go-next value items on-change)} ">"]]
    [select opts]))




