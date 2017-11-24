(ns demo.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.json :as json]
            [ring.middleware.stacktrace :as stacktrace])
  (:use demo.routes))

(def app
  (-> app-routes
      json/wrap-json-response
      json/wrap-json-body
      stacktrace/wrap-stacktrace-log
      keyword-params/wrap-keyword-params
      params/wrap-params))

(defn start-server []
  (jetty/run-jetty app {:host "0.0.0.0" :port 9000}))

(defn -main
  [& args]
  (start-server))