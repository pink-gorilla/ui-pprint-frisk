(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])

; ISS Location tracker - realtime

(system-start!
 (goldly/system
  {:id :iss-realtime
   :state {:first true
           :timestamp "not yet downloaded"}
   :html [:div
          (when (:first @state)
            (swap! state assoc :first false)
            (timeout ?download 3000)
            nil)
          [:h1 "iss time: " (:timestamp @state)]
          [:p "location "  (pr-str (:position @state))]
          [:p/leaflet [{:type :view :center (:position @state) :zoom 3 :height 600 :width 700}
                       {:type :marker :position (:position @state) :popup "the international space station is here!"}]]]
   :fns {}
   :fns-raw {:download (fn []
                     ;; http://open-notify.org/Open-Notify-API/ISS-Location-Now/
                         (let [r (GET "http://api.open-notify.org/iss-now.json"
                                   :response-format :json
                                   :keywords? true)
                               _ (info "r: " r)
                            ;     (js->clj)
                               p (get-in r [:iss_position])]
                           (info "iss pos:" p)
                           (swap! state assoc
                                  :timestamp (:timestamp r)
                                  :position
                                  [;(parse-float 
                                    (:latitude p)
                                   ;(parse-float 
                                    (:longitude p)])))}}
  {:fns {}}))







