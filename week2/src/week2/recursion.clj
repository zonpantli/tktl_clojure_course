(ns week2.recursion
  (use [week2.collections :only (halve)]))

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll) (product (rest coll)))))


;; (product [1 2 4])
;; =  (product (cons 1 (cons 2 (cons 4 nil))))
;; => (* 1 (product (cons 2  (cons 4 nil))))
;; => (* 1 (* 2 (product (cons 4 nil))))
;; => (* 1 (* 2 (* 4 (product nil))))
;; => (* 1 (* 2 (* 4 1))) 
;; => (* 1 (* 2 4))
;; => (* 1 8)
;; => 8


(defn last-element [coll]
  (if (empty? (rest coll))
      (first coll)
      (last-element (rest coll))))

(defn sequence-contains? [element a-seq]
  (cond
   (empty? a-seq)
   false
   (= (first a-seq) element)
   true
   :else
   (sequence-contains? element (rest a-seq))))

(defn seq= [seq-1 seq-2]
  (cond
   (and (empty? seq-1) (empty? seq-2))
   true
   (= (first seq-1) (first seq-2))
   (seq= (rest seq-1) (rest seq-2))
   :else
   false))

(defn power [n k]
  (if (zero? k)
    1
    (* n (power n (dec k)))))

(defn fib [n]
  (cond
   (zero? n)
   0
   (= 1 n)
   1
   :else
   (+ (fib (- n 1)) (fib (- n 2)))))


(defn my-range [up-to]
  (if (zero? up-to)
    nil
    (cons (dec up-to) (my-range (dec up-to)))))

(defn map-1 [f coll]
  (if (empty? coll)
    nil
    (cons (f (first coll))  (map-1 f (rest coll)))))

(defn tails [coll]
  (if (empty? coll)
    (list '())
    (cons (seq coll) (tails (rest coll)))))

(defn inits [coll]
  (if (empty? coll)
    (list '())
    (cons (seq coll) (inits (butlast coll)))))

(defn split-into-monotonics [coll]
  (if (empty? coll) nil
      (let [pairs  (map #(vector %1 %2) coll (rest coll))
            up (take-while (fn [[f l]] (<= f l)) pairs)
            down (take-while (fn [[f l]] (>= f l)) pairs)
            n (inc (max (count up) (count down)))]
        (cons  (take n coll) (split-into-monotonics (drop n coll))))))



(defn rotations-helper [i n coll]
     (if (> i n)
       '()
       (cons (take n coll) (rotations-helper (inc i) n (rest coll)))))

(defn rotations [coll]
  (rotations-helper 1 (count coll) (concat coll coll)))


(defn frequencies-helper [freqs coll]
  (if (empty? coll)
    {}
    (let [new-freqs (conj freqs (vector (first coll)
                                        (if (get freqs (first coll))
                                          (inc (get freqs (first coll)))
                                          1)))]
      (conj new-freqs (frequencies-helper new-freqs (rest coll))))))

(defn my-frequencies [coll]
  (frequencies-helper {} coll))




;; (defn un-frequencies [a-map]
;;   (reduce concat '() (map (fn [[key val]] (repeat val key)) a-map)))

(defn un-frequencies [a-map]
  (mapcat (fn [[key val]] (repeat val key)) a-map))

(defn seq-merge [seq1 seq2]
  (cond
   (empty? seq1)
   seq2
   (empty? seq2)
   seq1
   (<= (first seq1) (first seq2))
   (cons (first seq1) (seq-merge (rest seq1) seq2))
   :else
   (cons (first seq2) (seq-merge seq1 (rest seq2)))))


(defn mergesort [a-seq]
  (let [[b e] (halve a-seq)]
    (if (< (max (count b) (count e)) 2)
      (seq-merge b e)
      (seq-merge (mergesort b) (mergesort e)))))

(defn permutations [a-seq]
  (cond
   (empty? a-seq)
   '()
   (<= (count a-seq) 1)
   (list a-seq)
   :else
   (mapcat (fn [s] (map (fn [ss] (cons (first s) ss))
                       (permutations (rest s))))
           (rotations a-seq))))
 
(defn powerset-helper [i coll]
  (if (zero? i)
    nil
    (cons coll (powerset-helper (dec i) (map rest coll)))))

;; argh

(defn powerset [coll]
  (conj
   (mapcat set (powerset-helper (count coll)
                                (permutations coll)))
   '()))

(defn powerset [coll]
  (conj
   (seq
    (map seq
         (set (map set
                   (mapcat set (powerset-helper (count coll)
                                                (permutations coll)))))))
   '()))


