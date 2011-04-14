(ns java-interop.core
  (:import [java.util ArrayList Random Collections Comparator])
  (:use [criterium.core]))

(set! *warn-on-reflection* false)

(defn normalize [a-str]
  ((comp #(.trim %) #(.toLowerCase %)) a-str))


;; 2.236724 ms for 10000 Ints
(defn randoms [seed a-count]
  (let [r (Random. seed)]
    (loop [vec []
           cnt a-count]
      (if (zero? cnt)
        vec
        (recur (conj vec (.nextInt r)) (dec cnt))))))

;; 53.703556 ms
(defn randoms-2 [seed a-count]
  (let [r (Random. seed)]
    (vec (take a-count (map #(.nextInt %) (repeat r))))))

;; 7.914738 ms
(defn randoms-2-no-reflection [seed a-count]
  (let [r (Random. seed)]
    (vec (take a-count (map #(.nextInt ^Random %) (repeat r))))))

;;  1.992743 ms
(defn randoms-3 [seed a-count]
  (let [r (Random. seed)]
    (vec (for [i (range a-count)]
       (.nextInt r)))))


(defn arraylist-flatten [seq-of-seqs]
  (let [arr-l (ArrayList.)]
    (doseq [s seq-of-seqs]
      (.addAll arr-l s))
    (if (nil? (seq arr-l))
      '()
      arr-l)))


(defn callable-pony []
  (reify java.util.concurrent.Callable
         (call [this]
               :pony)))

(defn minimum-str [strings]
  (let [comp (reify java.util.Comparator
                    (compare [this str1 str2]
                             (.compareTo (count str1) (count str2))))]
       (java.util.Collections/min strings comp)))

(defn comparing [function]
  (reify java.util.Comparator
         (compare [this obj1 obj2]
                  (.compareTo (function obj1) (function obj2)))))

(defn sum-array [array]
  (apply + (seq array)))

(defn sum-array-2 [array]
  (areduce array i ret 0.0 (+ ret (aget array i))))

(defn fibo-array [amount]
  (let [fib-seq (fn [] ((fn fib [a b] 
                         (cons a (lazy-seq (fib b (+ a b)))))
                       0 1))]
    (into-array (take amount (fib-seq)))))

