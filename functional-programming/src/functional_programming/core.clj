(ns functional-programming.core)

(defn concat-all [seqs]
  (reduce concat '() seqs))

(defn string-cat [strs]
  (reduce #(str %1 " " %2) strs))

(defn seq-length [a-seq]
  (reduce (fn [cnt el] (inc cnt)) 0 a-seq))

(defn my-reverse [a-seq]
  (reduce conj '() a-seq))

(defn insert [a-seq el]
  (concat
   (concat (take-while (partial > el)  a-seq) (list el))
   (drop-while (partial > el) a-seq)))

(defn insertion-sort [a-seq]
  (reduce insert '() a-seq))

(defn seq-min-max [a-seq]
  (let [sorted (insertion-sort a-seq)]
    (vector (first sorted) (last sorted))))

(defn parity [a-seq]
  (let [pariteer (fn [s el] (if (get s el)
                             (disj s el)
                             (conj s el)))]
    (reduce pariteer #{} a-seq)))

(def my-double (partial * 2))

(def deep-double
     (partial map (partial map (partial * 2))))

(def my-empty? (comp zero? count))

(def find-first (comp first filter))

(def matrix-sum (comp (partial apply +) flatten))

(def first-result (comp first (partial filter (complement nil?)) map))
 
