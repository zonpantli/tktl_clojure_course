(ns life.visual
  (:use life.board)
  (:import [javax.swing JFrame JPanel SwingUtilities Timer]
           [java.awt Graphics Color Dimension]
           [java.awt.event ActionListener]))

(def cell-width 30)
(def cell-height 30)
(def update-delay 500)

(defn- color-for [cell]
  (if (dead? cell)
    Color/white
    Color/black))

(defn- draw-cell [board [x y :as coords] graphics]
  (doto graphics
    (.setColor (color-for (cell-at board coords)))
    (.fillRect (* cell-width x) (* cell-height y) cell-width cell-height)))

(defn- draw-board [board graphics]
  (doseq [coords (coordinates board)]
    (draw-cell board coords graphics)))

(defn- make-canvas-proxy [board]
  (proxy [JPanel ActionListener] []
    (paintComponent [graphics]
      (do (proxy-super paintComponent graphics)
        (draw-board @board graphics)))
    (getPreferredSize [] 
      (Dimension. (* cell-width (width @board))
                  (* cell-height (height @board))))
    (actionPerformed [event]
      (swap! board update)
      (.repaint this))))

(defn- make-canvas [board]
  (let [canvas (make-canvas-proxy board)]
    (.start (Timer. update-delay canvas))
    canvas))

(defn- make-frame []
  (JFrame. "Game of Life"))

(defn- make-window [board]
  (let [frame (make-frame)
        canvas (make-canvas board)]
    (doto frame
      (.setContentPane canvas)
      (.pack)
      (.show))))

(defn- make-game [board]
  (make-window (atom board)))

(defn run-game [board]
  (SwingUtilities/invokeLater #(make-game board)))
