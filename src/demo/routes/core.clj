(ns routes.core
  (use [compojure.core]
       [compojure.route]
       [ring.util.response :only [response created]]
       [db.user]))

;;定义路由
(defroutes app-routes
           (context "/api/user" []
             (GET "/:username/username" [username] (response (get-by-username username)))
             (POST "/" {body :body} (created (str (create-user! body)))))
           (context "/api/user/:id" [id]
             (PUT "/" {body :body} (update-user id body)))
           (not-found "<h1>page not fount</h1>"))
