(ns db.core
  (:use korma.db
        config.core))

;;创建数据库连接
(defdb db (mysql {:db       (-> config :datasource :db)
                  :user     (-> config :datasource :user)
                  :password (-> config :datasource :password)}))
