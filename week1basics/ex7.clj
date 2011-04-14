(defn foo [x]
  (if x
    "true"
    "false"))

(map foo [42 [] "" true nil false])
