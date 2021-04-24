(ns example.data.desc
  (:require
   [example.example :as example]))

(def data
  {:name "Margot Foster"
   :position "Backend Developer"
   :email "margotfoster@example.com"
   :salary "$120,000"
   :memo "Fugiat ipsum ipsum deserunt culpa aute sint do nostrud anim incididunt cillum culpa consequat. Excepteur qui ipsum aliquip consequat sint. Sit id mollit nulla mollit nostrud in ea officia proident. Irure nostrud pariatur mollit ad adipisicing reprehenderit deserunt qui eu."})

(def  cols {:name "Full name"
            :position "Application for"
            :email "Email address"
            :salary "Salary expectation"
            :memo ""})

(example/add
 :viz/desc
 [:p/description-list
  "Applicant Information"
  "Personal details and application."
  data
  cols])