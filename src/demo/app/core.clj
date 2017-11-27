(ns app.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.json :as json]
            [ring.middleware.stacktrace :as stacktrace])
  (:use routes.core
        config.core)
  (:import (org.apache.log4j BasicConfigurator)))

(def app
  (-> app-routes
      json/wrap-json-response
      json/wrap-json-body
      stacktrace/wrap-stacktrace-log
      keyword-params/wrap-keyword-params
      params/wrap-params))

(defn start-server []
  (BasicConfigurator/configure)
  (jetty/run-jetty app {:host (-> config :server :host)
                        :port (-> config :server :port)}))

(defn -main
  [& args]
  (start-server))