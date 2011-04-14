(defn string->number [string]
  (Integer/parseInt string))

(defn split-string [delimiter string]
  (vec (.split string delimiter)))n

(defn convert-string [string]
  (map string->number 
       (split-string "," string))
  )
