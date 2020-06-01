(ns pinkgorilla.controls.select
  (:require
   [reagent.core :as r]
   [pinkgorilla.controls.button :refer [button]]
   [pinkgorilla.ui.pinkie :refer [register-tag]]))

(defn css []
  [:style ".top-100 {top: 100%}
           .bottom-100 {bottom: 100%}
           .max-h-select {
              max-height: 300px;
           }
          "])

(defn- select-item [text selected? position select]
  (let [s (when selected? "border-teal-600")
        p (case position
            :first   "rounded-t"
            :last    "rounded-b"
            "")]
    [:div {:class (str "cursor-pointer w-full border-gray-100 border-b hover:bg-teal-100 " p)}
     [:div {:on-click #(select text)
            :class (str "flex w-full items-center p-2 pl-2 border-transparent bg-white border-l-2 relative hover:bg-teal-600 hover:text-teal-100 " s)}
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

(defn select [items val change-fn]
  (let [l (count items)
        l1 (- l 1)
        dropdown? (r/atom false)
        toggle-dropdown #(swap! dropdown? not)
        selected? (fn [name] (= name val))
        pos-key (fn [i]
                  (case i
                    0 :first
                    l1 :last
                    nil))
        select #(do (change-fn %)
                    (reset! dropdown? false))
        unselect #(change-fn "") ; #(reset! val-atom "")
        no-op #()]
    (fn [items val change-fn]
      [:<>
       [css]
       ;[:div {:class "flex-auto flex flex-col items-center"} ; h-64
       [:div {:class "flex flex-col items-center relative"}

        [:div {:class "w-full svelte-1l8159u"}
         [:div {:class "my-2 bg-white p-1 flex border border-gray-200 rounded svelte-1l8159u"}
          [:div {:class "flex flex-auto flex-wrap"}]
          [:input {:value val
                   :on-change no-op
                   :class "p-1 px-2 appearance-none outline-none w-full text-gray-800 svelte-1l8159u"}]
          [button-remove-selection unselect]
          [button-dropdown @dropdown? toggle-dropdown]]]

        (when @dropdown?
          [:div {:class (str "absolute shadow z-40 w-full lef-0 rounded max-h-select top-100" ; top-100
                             "overflow-y-auto svelte-5uyqqj")}
           [:div {:class "flex flex-col w-full"}
            (doall (map-indexed (fn [i v]
                                  ^{:key i}
                                  [select-item v (selected? v) (pos-key i) select]) items))]])]
;        ]
       ])))

#_(defn list-selector
    "combobox that is bound to an external atom.
      list is supplied"
    ([value-atom list action]
     (let [keys  {:on-change #(on-combo-changed- value-atom  list action %)} ;  #(reset! value-atom (.. % -target -value))
           keys  (if (nil? @value-atom) keys (assoc keys :value @value-atom))]
       [:select keys :value
        (when list (map-indexed (fn [idx item] [:option {:key idx :value item} item]) list))]))
    ([value-atom list]
     (list-selector value-atom list #(info (str "list selected: " %)))))

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

(defn nav-buttons [items v change-fn]
  [:<>
   [button {:on-click #(go-next v items change-fn)} "<"]
   [button {:on-click #(go-next v items change-fn)} ">"]])

(defn select-map
  "select one item from a seq of items
   parameters:
     options: this is an optional parameter, a map with keys:
              on-change fn with parameter selected-val 
              nav?      bool (show < > buttons)
     items: vec of selectable items
     val-atom: map inside an atom
     k: the key that the selected value is bound to in the val-atom

   examle:
   (def state reagent.core/atom {:letter \"a\"})
   [select-map [\"a\" \"b\"] state :letter]
  "
  ([items val-atom k]
   (select-map {:nav? false} items val-atom k))
  ([{:keys [on-change nav?] :as options} items val-atom k]
   (let [change-fn (fn [v]
                     (swap! val-atom assoc k v)
                     (when on-change (on-change v)))]
     [:<>
      [select items (k @val-atom) change-fn]
      (when nav?
        [nav-buttons items (k @val-atom) change-fn])])))

(register-tag :p/pselect select)
(register-tag :p/pselectm select-map)