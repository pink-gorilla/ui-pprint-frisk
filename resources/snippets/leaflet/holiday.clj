(require '[goldly.runner :refer [system-start!]])
(require '[goldly.system :as goldly])

(def places
  {:london {:center [51.49, -0.08]
            :zoom 12
            :height 600 :width 700
            :features [{:type :rectangle
                        :bounds [[51.49, -0.08]
                                 [51.5, -0.06]]}]}
   :panama {:center [9.1880621 -82.0075993]
            :zoom 12
            :height 600 :width 700}
   :vienna {:center [48.2215786 16.2684791]
            :zoom 12
            :height 600 :width 700}})

(system-start!
 (goldly/system
  {:id :holiday
   :state {:place nil
           :map nil}
   :html [:<>
          [:div {:class "flex flex-row content-between"}
           [:p/select
            {:items [:london :panama :vienna]
             :on-change ?getdestination}
            state [:place]]
           [:p/button {:on-click ?lucky} "Feeling Lucky!"]]
          [:p (str "map data: " (:map @state))]
          (when (:map @state)
            [:p/leaflet (:map @state)])]
   :fns {}}
  {:fns {:getdestination [(fn [place] (place places))
                          [:map]]
         :lucky [(fn [] (let [ks (into [] (keys places))
                              i (rand-int (count ks))
                              place (get ks i)]
                          (place places)))
                 [:map]]}}))
