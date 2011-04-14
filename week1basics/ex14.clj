(def cities {:author "China Miéville" :title "The City and the City"})

(def books {{:author "China Miéville" :title "The City and the City"} 500
            {:author "Haruki Murakami" :title "Norwegian Wood"} 400
            {:author "Guy Gavriel Kay" :title "Under Heaven"} 576})


(defn add-number-of-pages [books auth-title]
  (conj auth-title [:number-of-pages (books auth-title)]))
