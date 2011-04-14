(ns week2.predicates)


(defn generic-doublificate [doublificatee]
  (cond
   (number? doublificatee) (* 2 doublificatee)
   (and (coll? doublificatee) (empty? doublificatee)) nil
   (or (vector? doublificatee) (list? doublificatee)) (map #(* 2 %) doublificatee)
   :else true))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn empty-string? [string]
  (every? whitespace? string))

(defn pred-and [pred1 pred2]
  (fn [x] (and (pred1 x) (pred2 x))))

(defn every-book-has-a-title? [books]
  (every? :title books))

(defn first-value-for-key [key maps]
  (some key maps))

(defn prime? [num]
  (let [p (fn [x] (= (rem num x) 0)) ]
    (not (some p (range 2 num)))))
