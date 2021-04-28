(ns demo.routes)

(def routes-app
  {"" :demo/main
   ["examples/" :category] :demo/example
   "test" :demo/test
   })

(def routes-api
  {})
