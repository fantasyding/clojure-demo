(ns demo.db
  (:use korma.db
        korma.core))

;;创建数据库连接
(defdb db (mysql {:db       "coincard"
                  :user     "root"
                  :password "root"}))

(defentity users (table :t_oauth_user))

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