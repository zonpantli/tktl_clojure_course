(ns life.test-board
  (:use life.board
        lazytest.deftest
        [clojure.string :only (join)]))

(defmacro >>> [& args]
  `(it ~(str args) ~@args))

(deftest a-new-board
  (let [board (make-board 5 7)
        boo (set-alive board [0 0])
        booo (set-dead boo [0 0])]
    (is (dead? (cell-at board [0 0])))
    (is (alive? (cell-at boo [0 0])))
    (is (dead? (cell-at booo [0 0])))))

(defn- cells-with-middle-removed [board]
  (let [middle [(int (/ (width board) 2))
                (int (/ (height board) 2))]]
    (for [coords (coordinates board)
          :when (not= coords middle)]
      (cell-at board coords))))

(deftest test-neighbourhood
  (let [board (read-board ["..."
                           ".#."
                           "..."])
        upper-left-neighbourhood
        (cells-with-middle-removed
          (read-board ["..."
                       "..."
                       "..#"]))]
    (is (= (neighbourhood board [0 0])
           upper-left-neighbourhood))
    (is (= (neighbourhood board [1 1])
           (cells-with-middle-removed board)))))

(deftest test-update
  (let [should-die (read-board ["..."
                                ".#."
                                "..."])
        empty-board (read-board ["..."
                                 "..."
                                 "..."])
        stable (read-board ["...."
                            ".##."
                            ".##."
                            "...."])
        flip-1 (read-board ["....."
                            "..#.."
                            "..#.."
                            "..#.."
                            "....."])
        flip-2 (read-board ["....."
                            "....."
                            ".###."
                            "....."
                            "....."])]
    (is (= (update empty-board) empty-board))
    (is (= (update should-die) empty-board))
    (is (= (update stable) stable))
    (is (= (update flip-1) flip-2))
    (is (= (update flip-2) flip-1))))

(deftest test-show-board
  (let [test-boards [["..."
                      ".#."
                      "..."]
                     [".#."]
                     ["...."
                      ".##."
                      ".##."
                      "...."]]]
    (every? #(is (= (show-board (read-board %))
                    %))
            test-boards)))
