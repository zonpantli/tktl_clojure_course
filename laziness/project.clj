(defproject laziness "0.0.1-SNAPSHOT"
            :description "Laziness"
            :dependencies [[clojure "1.2.0"]
                           [midje "1.0.1"]]
            :dev-dependencies[[swank-clojure "1.2.1"]
                              [lein-cdt "1.0.0"]
                              [criterium "0.0.1-SNAPSHOT"]]
            :hooks [leiningen.hooks.cdt])
