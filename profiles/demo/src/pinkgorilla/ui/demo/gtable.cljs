(ns pinkgorilla.ui.demo.gtable)



(def data [[:p "hi"] [:p "ho"]])

(def demo
  [:div.table-demo
   [:p/gtable (repeat 3 data)]])