(ns pinkgorilla.ui.control.bind)

(defn bind [control]
  (fn [{:keys [on-change] :as options} a path]
    (let [val (if path
                (get-in @a path)
                @a)
          change-fn (fn [v]
                      (if path
                        (swap! a assoc-in path v)
                        (reset! a v))
                      (when on-change (on-change v)))]
      [control (merge options
                      {:on-change change-fn
                       :value val})])))