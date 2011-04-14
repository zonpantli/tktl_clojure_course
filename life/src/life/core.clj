(ns life.core
  (:use life.visual life.board
        [clojure.java.io :only (reader)])
  (:gen-class))

(defn read-file [filename]
  (read-board (doall (line-seq (reader filename)))))

(defn -main [& args]
  (println "Game of Life" args)
  (run-game (read-file (first args))))
