(defn consecutives [v]
  (let [v1 v
        v2 (next v)]
    (map vector v1 v2))
  )
