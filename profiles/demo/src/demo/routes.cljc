(ns demo.routes
  (:require
   [webly.web.resources :refer [resource-handler]]))

(def demo-routes-app
  {"" :demo/main})

(def demo-routes-frontend
  ["/" demo-routes-app])

(def demo-routes-backend
  ["/" {"" demo-routes-app
        "r" resource-handler}])
