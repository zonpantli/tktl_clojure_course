(def cities {:author "China Miéville" :title "The City and the City"})

(defn title-length [book]
  (let [title (:title book)]
    (count title)
  )
)
