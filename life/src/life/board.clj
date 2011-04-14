(ns life.board)

(defrecord Board [width height cells])

(def ^{:private true} alive :alive)
(def ^{:private true} dead :dead)

(def height :height)

(def width :width)

(def alive? (partial = alive))

(def dead? (partial = dead))

(def ^{:private true} vec-repeat
  (comp vec repeat))  

(defn make-board [width height]
  (let [row (vec-repeat width dead)
        cells (vec-repeat height row)]
    (Board. width height cells)))

(defn coordinates [board]
  (for [row (range (height board))
        col (range (width board))]
    [col row]))

(defn cell-at [board [column row]]
  (let [x (mod column (width board))
        y (mod row (height board))]
    (get-in board [:cells y x])))

(defn- set-status [board [column row] status]
  (update-in board [:cells row column] (constantly status)))

(defn set-alive [board coordinates]
  (set-status board coordinates alive))

(defn set-dead [board coordinates]
  (set-status board coordinates dead))

(defn- same-length? [seq-of-seqs]
  (apply = (map count seq-of-seqs)))
 
(defn build-board [cells]
  {:pre [(same-length? cells)
         (every? #{alive dead} (flatten cells))]}
  (let [height (count cells)
        width (count (first cells))]
    (Board. width height cells)))

(defn- show-cell [cell]
  (condp = cell
    dead \.
    alive \#))

(defn show-board [board]
  (let [show-row #(apply str (map show-cell %))]
    (map show-row (get board :cells))))

(defn- read-cell [cell]
  (case cell
    \. dead
    \# alive))

(defn read-board [cells]
  (let [read-row #(vec (map read-cell %))]
    (build-board (vec (map read-row cells)))))

(defn range-around [x]
  (range (- x 1) (+ x 2)))

(defn neighbourhood [board [column row]]
  (for [x (range-around column)
        y (range-around row)
        :when (or (not= column x) (not= row y))]
    (cell-at board [x y])))

(defn count-alive-neighbours [board coords]
  (count (filter alive? (neighbourhood board coords))))

(defn- update-cell [board coords]
  (let [cell-alive (alive? (cell-at board coords))
        neighbours-alive (count-alive-neighbours board coords)]
    (cond
     (and cell-alive (< neighbours-alive 2))
     dead
     (and cell-alive (> neighbours-alive 3))
     dead
     cell-alive
     alive
     (and (not cell-alive) (= neighbours-alive 3))
     alive
     :else
     dead)))

(defn update [board]
  (build-board (vec (map vec (partition (width board)
                                        (for [coords (coordinates board)]
                                          (update-cell board coords)))))))


