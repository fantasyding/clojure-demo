(ns routes.core
  (use [compojure.core]
       [compojure.route]
       [ring.util.response :only [response created]]
       [db.user]))

;;定义路由
(defroutes app-routes
  (context "/api/users" []
    (GET "/" {params :params} (response (get-users params)))
    (GET "/:username/username" [username] (response (get-by-username username)))
    (POST "/" {body :body} (created (str (create-user! body)))))
  (context "/api/users/:id" [id]
    (PUT "/" {body :body} (response (update-user id body)))
    (DELETE "/" [] (do (delete-by-id id) {:status 204})))
  (not-found "<h1>page not fount</h1>"))
