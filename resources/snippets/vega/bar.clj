(require '[goldly.runner :refer [system-start!]])
(require '[goldly.system :as goldly :refer [def-ui]])

(def-ui bar
  {:$schema "https://vega.github.io/schema/vega-lite/v4.json"
   :description "A simple bar chart with embedded data."
   :width 200 ; "container"
   :height 200 ; "container"
   :data {:values [{:a "A" :b 28} {:a "B" :b 55} {:a "C" :b 43} {:a "D" :b 91}
                   {:a "E" :b 81} {:a "F" :b 53} {:a "G" :b 19} {:a "H" :b 87}
                   {:a "I" :b 52} {:a "J" :b 127}]}
   :mark "bar"
   :encoding {:x {:field "a" :type "ordinal"}
              :y {:field "b" :type "quantitative"}}})


(system-start!
 (goldly/system
  {:id :vega-bar
   :state {}
   :html [:p/vega bar]
   :fns {}}
  {:fns {}}))




