(ns pinkgorilla.ui.app.card)

(defn ^{:category :layout}
  card [{:keys [heading text img href]}]
  [:<>
   [:a {:href href}
    [:div {:class "max-w-sm px-4 py-2 m-2 font-mono text-lg text-gray-800 text-center border-gray-600 border rounded-lg bg-white
                         shadow-lg hover:bg-black hover:text-white duration-300"}
     [:h2 {:class "pt-2 whitespace-no-wrap content-center overflow-scroll"}
      heading]
     [:hr {:style {:marginLeft "20%" :marginRight "20%"}}]
     [:div {:class "flex text-sm justify-center m-4"}
      text]

     [:div {:class "flex justify-center"}
      [:img {:class "w-11 h-11 rounded-full mr-4 shadow-lg border-2 border-black"
             :src (if img img "unknown.jpg")}]]]]])


