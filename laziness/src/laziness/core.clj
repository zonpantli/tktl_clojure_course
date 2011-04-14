(ns laziness.core
  (:use [clojure.java.io :only (reader as-url)]))


(defn url-get [url]
  (with-open [in (reader (as-url url))]
    (doall (line-seq in))))

(defn counting-spider [urls]
  (doall (for [url urls]
           (do (println "Fetching" url)
               (count (url-get url))))))

(defn print-squares [up-to]
  (doseq [val (range 0 up-to)]
    (println (* val val))))

(first (filter #(and (not= % 0) (= (rem % 72) 0) (= (rem % 108) 0)) (range)))

(defn divisible-by-all-under? [n val]
  (every? #(= (rem val %) 0) (range 1 (inc n))))

(defn super-composite [n]
  (first (filter (partial divisible-by-all-under? n) (map inc (range)))))

(defn indexed
  "Maps elements of a sequence from element to [index-in-sequence element]."
  [s]
  (map vector (range) s))

(defn indexes [a-seq]
  (map first (indexed a-seq)))

(defn inits [a-seq]
  (concat '(())
          (map take  (map inc (indexes a-seq)) (repeat a-seq))))

(defn halves [a-seq]
  (map split-at (indexes a-seq) (repeat a-seq)))

(defn sum-halve [a-seq]
  (first (filter (fn [[f l]] (= (apply + f) (apply + l))) (halves a-seq))))

(defn nonempty-tails [a-seq]
  (take-while (complement empty?) (iterate rest a-seq)))


;; these work for finite seqs only
;; (defn subseqs [a-seq]
;;   (mapcat #(take-while (complement empty?) (iterate drop-last %))
;;           (nonempty-tails a-seq)))

;; this works for infinite seqs only
;; (defn subseqs [a-seq]
;;   (mapcat nonempty-tails (map take (range) (repeat a-seq))))


(defn subseqs [a-seq]
  (let [heads (map take (range) (repeat a-seq))]
    (mapcat (comp nonempty-tails second)
            (take-while #(not= (first %) (first (rest (rest %))))
                        (map vector heads (rest heads) (rest (rest heads)))))))

(defn subseq-sum [target a-seq]
  (first (filter #(= target (apply + %)) (subseqs a-seq))))



;|

