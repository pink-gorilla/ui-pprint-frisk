(ns pinkgorilla.ui.demo.gtable)



(def data [[:p "hi"] [:p "ho"]])

(def demo
  [:div.table-container
   [:gtable (repeat 3 data)]])