(ns db.user
  (:require [clojure.tools.logging :as log])
  (:use db.core
        korma.core)
  (:import (java.util Date)))

(declare users)

(defentity users (table :t_user))

(def select-users (-> (select* users)
                      (fields :id :username :service_name :service_phone)
                      (order :created :desc)))

(defn get-users
  "get users by params"
  [params]
  (log/infof "get users by params: %s" params)
  (let [{username      :username
         service_name  :service_name
         service_phone :service_phone
         status        :status
         pageNo        :pageNo
         pageSize      :pageSize} params]
    (-> (cond-> select-users
                (not (empty? username)) (where {:username username})
                (not (empty? service_name)) (where {:service_name service_name})
                (not (empty? service_phone)) (where {:service_phone service_phone})
                (not (empty? status)) (where {:status status})
                (not (empty? pageSize)) (limit (Integer/parseInt pageSize))
                (not (empty? pageNo)) (offset (* (Integer/parseInt pageSize) (- (Integer/parseInt pageNo) 1)))
                ) (select))
    ))

(defn get-by-id
  "query user by id"
  [id]
  (-> (select users
              (fields :id :username :service_name :service_phone)
              (where {:id id}))
      first))

(defn get-by-username
  "query user by username"
  [username]
  ;;sql-only 打印sql
  ;;dry-run 打印sql+参数
  (-> (select users
              (fields :id :username :service_name :service_phone)
              (where {:username username}))
      first))

(defn create-user!
  "create user"
  [user]
  (log/infof "create user: %s" user)
  (:generated_key (insert users (values user))))

(defn update-user
  "update user"
  [id body]
  (log/infof "update user: %s" body)
  (let [user (assoc body "id" id "update_time" (Date.))]
    ;异常处理
    (try
      (korma.core/update users (set-fields user) (where {:id id}))
      (catch Exception e
        (log/error e "update user error")
        (throw (IllegalStateException. "update user error"))
        ))
    )
  (get-by-id id)
  )

(defn delete-by-id
  "delete user by id"
  [id]
  (delete users (where {:id id})))