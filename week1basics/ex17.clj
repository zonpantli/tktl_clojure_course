(def books [{:author "China Miéville" :title "Kraken"} 
            {:author "China Miéville" :title "The City and the City"} 
            {:author "Haruki Murakami" :title "Norwegian Wood"} 
            {:author "Guy Gavriel Kay" :title "Under Heaven"}])

(defn books-by-author [auth books]
  (map :title (filter #(= (:author %)  auth) books))
  )



