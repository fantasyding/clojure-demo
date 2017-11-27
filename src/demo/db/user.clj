(ns db.user
  (:require [clojure.tools.logging :as log])
  (:use db.core
        korma.core))

(declare users)

(defentity users (table :t_user))

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
  [id user]
  (log/infof "update user: %s" user)
  (korma.core/update users)
  )