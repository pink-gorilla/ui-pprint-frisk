(require '[goldly.system :as goldly])
(require '[goldly.runner :refer [system-start!]])


(defn data [nr]
  (let [d (vec (take nr (repeatedly rand)))
       ; _ (println "d is:" d)
        ]
    d))


(goldly/def-ui data-20 (data 20))
(goldly/def-ui data-40 (data 40))
(goldly/def-ui data-100 (data 100))
(goldly/def-ui data-150 (data 150))

(system-start!
 (goldly/system
  {:id :sparkline
   :state {}
   :html [:div
           [spark/line {:data data-20 :limit 20
                        ;:width 100 :height 20
                        ;:svgWidth 300 :svgHeight 20
                        ;:margin 5
                        }]


          [spark/line {:data data-20 :limit 20
                       :width 100 :height 20
                       :svgWidth 300 :svgHeight 20
                       :margin 5}]

          [:p/sparkline {:data data-20 :limit 20
                         :width 100 :height 20
                         :svgWidth 300 :svgHeight 20
                         :margin 5}]
          [:p/sparkline {:data data-40 :limit 40
                         :width 100 :height 20
                         :svgWidth 300 :svgHeight 20
                         :margin 5}]
          [:p/sparklinespot {:data data-100 :limit 100
                             :svgWidth 300 :svgHeight 20
                             :margin 10}]
          [:p/sparklinebar {:data [50, 10, 5, 20, 10 6 7 88 50 30 60] :limit 10
                            :svgWidth 300 :svgHeight 20
                            :margin 10}]
          [:p/sparklinebar {:data data-150 :limit 50
                            :svgWidth 300 :svgHeight 20
                            :margin 1}]]
   :fns {}}))
