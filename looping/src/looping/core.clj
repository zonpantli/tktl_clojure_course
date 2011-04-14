(ns looping.core)

(defn power [base exp]
  (let [helper (fn [acc base exp]
                 (if (zero? exp)
                   acc
                   (recur (* acc base) base (dec exp))))]
    (helper 1 base exp)))

(defn last-element [a-seq]
  (if (<= (count a-seq) 1)
    (first a-seq)
    (recur (rest a-seq))))

(defn seq= [seq1 seq2]
  (cond
   (and (empty? seq1) (empty? seq2))
   true
   (not= (first seq1) (first seq2))
   false
   :else
   (recur (rest seq1) (rest seq2))))

(defn find-first-index [pred a-seq]
  (loop [i 0
         pred pred
         a-seq a-seq]
   (cond
    (empty? a-seq )
    nil
    (pred (first a-seq))
    i
    :else
    (recur (inc i) pred (rest a-seq)))))

(defn avg [a-seq]
  (loop [sum 0
         n 0
         a-seq a-seq]
    (if (empty? a-seq)
      (/ sum n)
      (recur (+ sum (first a-seq)) (inc n) (rest a-seq)))))

(defn parity [a-seq]
  (loop [odds #{}
         a-seq a-seq]
    (cond
     (empty? a-seq)
     odds
     (get odds (first a-seq))
     (recur (disj odds (first a-seq)) (rest a-seq))
     :else
     (recur (conj odds (first a-seq)) (rest a-seq)))))

(defn fast-fibo [n]
  (loop [F0 0
         F1 1
         n n]
    (cond
     (zero? n)
     F0
     (= 1 n)
     F1
     :else
     (recur F1 (+ F0 F1) (dec n)))))


(defn cut-at-repetition [a-seq]
  (loop [uniqs []
         a-seq a-seq]
    (if (or (empty? a-seq) (some #(= (first a-seq) %) uniqs))
      uniqs
      (recur (conj uniqs (first a-seq)) (rest a-seq)))))
