(ns life.atom)

(def *log* (atom []))

(defn new-log []
  (do (swap! *log* #(empty %)) nil))

(defn log [str]
  (do (swap! *log* #(conj % str)) nil))

(defn print-log []
  (doseq [line (map #(vector %1 %2) (range) @*log*)]
    (println "<" (first line) "> " (second line))))


(defstruct log-entry :text :line)

(defn log-1 [str]
  (let [line-cnt (count @*log*)]
    (do
      (swap! *log* #(conj % (struct log-entry str line-cnt))))
    nil))

(defn print-log-1 []
  (doseq [line @*log*]
    (println "<" (:line line) "> " (:text line))))
