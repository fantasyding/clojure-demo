(ns db.user
  (:use db.core
        korma.core))

(defentity users (table :t_user))

(defn get-by-username
  "query user by username"
  [username]
  ;;sql-only 打印sql
  ;;dry-run 打印sql+参数
  (-> (select users
              (fields :id :username :service_name :service_phone)
              (where {:username username}))
      first)
  )