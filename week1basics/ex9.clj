(defn teen? [age]
  (and (>= age 13) (<= age 19))
  )

(defn not-teen? [age]
  (not (teen? age)))
