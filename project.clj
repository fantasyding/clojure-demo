(defproject demo "1.0.0"
  :description "Just do it!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.3"]
                 [compojure "1.6.0"]
                 [ring/ring-json "0.5.0-beta1"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [korma "0.4.3"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [cprop "0.1.11"]]
  :plugins [[lein-ring "0.12.1"]]
  :ring {:handler        demo.core/app
         :auto-reload?  true
         :auto-refresh? true}
  :main ^:skip-aot app.core
  :source-paths ["src/demo"]
  :resource-paths ["resources"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
