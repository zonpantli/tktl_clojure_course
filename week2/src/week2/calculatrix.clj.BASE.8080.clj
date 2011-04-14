(ns week2.calculatrix)

(defn function-table [key]
  (let [table {"+" {:f (fn [x y] (+ x y)) :arity 2}
              "-"  {:f (fn [x y] (- x y))  :arity 2}
               "*" {:f (fn [x y] (* x y))  :arity 2}
               "avg" {:f (fn [x y] (/ (+ x y) 2)) :arity 2}
               "pow" {:f (fn [b e] (int (Math/pow b e))) :arity 2}}]
    (get table key )))



(comment
 (defn read-words []
   "Read a line and split it into words. Returns the words as a vector
  of strings."
   (if-let [line (read-line)]
     (vec (.split line "\\s+")))))
 

(defn read-words []
  "Read a line and split it into words. Returns the words as a vector
of strings. Loops until a line is available."
  (if-let [line (read-line)]
    (vec (.split line "\\s+"))
    (recur)))

(defn string->number [string]
  (try
    (Integer/parseInt string)
    (catch NumberFormatException e nil)))

(defn prepare-operand [command arg last-result var-table]
  (cond
   (= arg "_")
   last-result 
   (get var-table arg)
   (get var-table arg)
   (and (= command "store") (nil? (string->number arg)))
   arg
   :else
   (string->number arg)))

(defn compute [command args & state]
  "Takes a command and a sequence of arguments, returns computed value
  or nil if given an unknown command or non-integer operands."
  (let [last-result (first state)
        var-table   (conj {} (second state))
        first-operand (prepare-operand command (first args) last-result var-table)
        second-operand (prepare-operand command (second args) last-result var-table)]
    (cond
     (and (= command "store") (and first-operand second-operand))
     (vector (conj var-table (vector first-operand second-operand))
             (conj var-table (vector first-operand second-operand))) ;; argh
     (nil? first-operand)
     (vector (str "Invalid operand: " (first args)) var-table)
     (nil? second-operand)
     (vector (str "Invalid operand: " (second args)) var-table)
     (and first-operand second-operand)
     (if-let [fun (function-table command)]
       (let [f (get fun :f)
             arity (get fun :arity)
             arg-count (count args)]
         (if (= arity arg-count)
           (vector (f first-operand second-operand) var-table)
           (vector (str "Wrong number of arguments to " command ": expects " arity ", you gave " arg-count ".")
                   var-table)))
       (vector (str "Invalid command: " command) var-table)))))

(defn exit? [word]
  (= word ""))

(defn exit []
  )



(defn main [ & state]
  "This is the driver loop of the calculator. It loops by calling itself recursively."
  (let [last-result (first state)
        var-table (second state)
        words (read-words)
        command (first words)
        arguments (rest words)
        exit-flag (exit? (first words))
        [result var-table] (if-not exit-flag
                             (compute command arguments last-result var-table)
                             (vector nil nil))]
    (if exit-flag  
      (exit)
      (do
        (if-not (= (class result) clojure.lang.PersistentArrayMap)
          (println "  =>"  result)) 
       (main result var-table)))))
