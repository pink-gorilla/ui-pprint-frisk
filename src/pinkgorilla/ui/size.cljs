(ns pinkgorilla.ui.size)

(defn size [s]
  ; either both pixels, or both percentage.
  (case  s
    :small {:style {:height "150px"
                    :width "600px"}}
    :medium {:style {:height "400px"
                     :width "600px"}}
    :full   {:style {:height "100%"
                     :width "100%"}}
    {:style {:height "150px"
             :width "600px"}}))