(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])

; ISS Location tracker - realtime

(system-start!
 (goldly/system
  {:id :iss-realtime
   :state {:first true
           :data "not yet downloaded"}
   :html [:div
          (when (:first @state)
            (swap! state assoc :first false)
            (interval ?download 3000)
            nil)
          [:p "raw data: " (pr-str (:data @state))]
          (when-let [p (?pos)]
            [:<>
             [:p "location "  (pr-str p)]
             [:p/leaflet {:center p
                          :zoom 3
                          :height 600
                          :width 700
                          :features [{:type :marker
                                      :position p
                                      :popup "the international space station is here!"}]}]])]
   :fns {}
   :fns-raw {:pos (fn [] ;{ :iss_position {:longitude "-111.4007", :latitude "2.9204"}}
                    (when-let [lng (get-in @state [:data :iss_position :longitude])]
                      (when-let [lat (get-in @state [:data :iss_position :latitude])]
                        [(parse-float lat) (parse-float lng)])))
             :download (fn []
                     ;; http://open-notify.org/Open-Notify-API/ISS-Location-Now/
                         (info "downloading iss pos..")
                         (get-json "http://api.open-notify.org/iss-now.json" state [:data]))}}
  {:fns {}}))
