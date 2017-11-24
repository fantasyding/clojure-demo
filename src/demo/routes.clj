(ns demo.routes
  (use [compojure.core]
       [compojure.route]
       [ring.util.response :only [response]]
       [demo.db]))

;;定义路由
(defroutes app-routes
    (GET "/api/user/:username" [username] (response (get-by-username username)))
           (not-found "<h1>page not fount</h1>"))
