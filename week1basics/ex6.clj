(defn fizzbuzz [x]
  (cond
   (and (= (mod x 3) 0) (= (mod x 5) 0))
   "gotcha!"
   
   (= (mod x 3) 0)
   "fizz"

   (=(mod x 5) 0)
   "buzz"

   ))
