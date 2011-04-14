(defn sort-by-keys [m]
  (for [key
        (sort
         (keys m))]
    (m key)))
