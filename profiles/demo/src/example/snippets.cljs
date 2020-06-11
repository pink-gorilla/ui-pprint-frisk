(ns example.snippets
  (:require
   [demo.example :as example]))

(def data
  [{:session "3ced9967-e16c-4e47-a661-53b2d8527d96"
    :id 242
    :ns nil
    :code "(for [i (range 10)]
             (println \"hello, world!\"))"
    :value nil
    :pinkie nil
    :out "hello, \r\n world!"}
   {:session "840dadb8-3cd2-486a-bf0f-ccf695804c81"
    :id "86"
    :ns "goldy.nrepl.client"
    :code "(pinkie.converter/R [:p/vega (+ 8 8)])"
    :value [:p/vega 16]
    :pinkie [:p/vega
             {:data {:values [{:x 3 :y 4} {:x 7 :y 1}]}
              :mark :point
              :encoding {:x {:field :x :type :quantitative} :y {:field :y :type :quantitative}}}]
    :out nil}
   {:session "3ced9967-e16c-4e47-a661-53b2d8527d96"
    :id "323", :ns "goldy.nrepl.client"
    :code "[66 (+ 8 8)]"
    :value [66 "16"]
    :pinkie [:div "[" [:div {:class "clj-vector"} [:span {:class "clj-keyword"} ":p"] [:span {:class "clj-long"} "16"]] "]"]
    :out "LOG LONG: jk k jk l  l kj hlkh lkj lk hkjh kj j hj kh kjh jhg jhg kh jh gj  k hg kg kjgh k  jh gkj kj kjh gkj ghj kjk kh kh gjhg k gkjjkh g hjhj kj kjl ljk lkj jkl ljk lkj kj klj ljk lkj l jk kljh lkj lkj lkj lkj lkj lkj "}])


(example/add
 "snippets"
 [:div.snippets-demo
  [:p/snippets data]])
