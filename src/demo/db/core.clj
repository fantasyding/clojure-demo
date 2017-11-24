(ns db.core
  (:use korma.db
        korma.core
        config.core))

;;创建数据库连接
(defdb db (mysql {:db       (-> config :datasource :db)
                  :user     (-> config :datasource :user)
                  :password (-> config :datasource :password)}))

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