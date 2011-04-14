(ns boss1.core)

(defn author-has-years? [book]
  (and
   (contains? (:author book) :birth-year)
   (contains? (:author book) :death-year)
   )
  )

(defn books-with-author-years [books]
  (filter author-has-years? books)
  )

(defn authors [books]
  (distinct (map :author books))
  )

(defn author-names [books]
   (map :name (authors books)))

(defn titles-by-author [author books]
  (map :title
       (filter #(= (:author %) author) books ))
  )

(defn build-map [key-val-pairs]
  (reduce conj {} key-val-pairs))

(defn author-catalog [books]
  (build-map
     (map #(conj [%] (titles-by-author % books) ) (authors books))
   ))
