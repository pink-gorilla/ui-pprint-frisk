(ns pinkgorilla.ui.config)

(def default {:prefix "/r/"})

(def config (atom default))

(defn set-prefix! [prefix]
  (swap! config assoc :prefix prefix))

(defn res-href [href]
  (str (:prefix @config) href))

(defn link-css [href]
  [:link {:rel "stylesheet"
          :href (res-href href)}])
