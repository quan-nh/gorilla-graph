(defproject gorilla-graph "0.1.0-SNAPSHOT"
  :description "A network graph renderer for Gorilla REPL, using vis.js library."
  :url "https://github.com/tentamen/gorilla-graph"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [gorilla-renderable "2.0.0"]
                 [cheshire "5.7.1"]]
  :plugins [[lein-gorilla "0.4.0"]])
