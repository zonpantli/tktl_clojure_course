(ns week2.collections)

(defn doublificate [doublificatee]
  (into doublificatee
        (map #(vector (str "double-" (first %))
                      (* 2 (last %)))  (seq doublificatee))))

(defn halve [collection]
  (let [split-point (/ (count collection) 2)]
    (split-at split-point collection)))

(defn first-five-positives [a-seq]
  (take 5 (filter pos? a-seq)))

(defn snip [a-seq]
  (vector (take-while #(not= :snip %) a-seq)
          (rest (drop-while #(not= :snip %) a-seq))))

(defn describe-books [books]
  (apply str
         (cons (str "I have " (count books) " books.")
               (map #(str " " (:title %) " was written by " (:author %) ".") books))))

(defn monotonic? [a-seq]
  (or (every? true? (map >= (rest a-seq) a-seq))
      (every? true? (map <= (rest a-seq) a-seq))))

(defn transpose [matrix]
  (if (empty? matrix)
    ()
    (apply map list matrix)))

(defn exterminate-1 [a-map]
  (conj {} (filter #(<= (first %) (last %)) a-map)))

(defn exterminate [a-map]
  (conj {} (filter #(<= (first %) (last %)) a-map)))


(defn take-3 [a-seq]
  (let [[fst scnd thrd] a-seq]
    (vector fst scnd thrd)))

(defn author-to-string [author]
  (let [{name :name
         birth :birth-year
         death :death-year} author]
    (str name " (" birth "-" death ")" )))

(defn book-to-string [{title :title author :author}]
  (str "A book, " title ", written by " (author-to-string author)))

(defn my-keys [a-map]
  (for [[key val] (seq a-map)]
    key))

(defn who-wrote [books index]
  (get-in books [index :author]))

(defn author-birth-years [books]
  (filter (complement nil?)
          (map #(get-in % [:author :birth-year]) books)))

(defn add-at [matrix a b]
  (update-in matrix a
          #(+ % (get-in matrix b))))

(defn monotonic-prefix [a-seq]
  (let [asc  (take-while true? (map <= (rest a-seq) a-seq))
        desc (take-while true? (map >= (rest a-seq) a-seq))]
    (if (> (count asc) (count desc))
      (take (inc (count asc)) a-seq)
      (take (inc (count desc)) a-seq))))

;)
