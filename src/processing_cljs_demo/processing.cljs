(ns ^:figwheel-always processing-cljs-demo.processing)

(defn size [pjs-obj width height]
  (.size pjs-obj width height)
  pjs-obj)

(defn image-mode [pjs-obj mode]
  (.imageMode pjs-obj (aget pjs-obj mode))
  pjs-obj)

(defn no-stroke [pjs-obj]
  (.noStroke pjs-obj)
  pjs-obj)

(defn background [pjs-obj gray-scale]
  (.background pjs-obj gray-scale)
  pjs-obj)

(defn fill [pjs-obj color depth]
  (.fill pjs-obj color depth)
  pjs-obj)

(defn rect [pjs-obj x y w h]
  (.rect pjs-obj x y w h)
  pjs-obj)

(defn exit [pjs-obj]
  (.exit pjs-obj)
  pjs-obj)

(defn setup! [pjs-obj f]
  (set! (.-setup pjs-obj) f)
  pjs-obj)

(defn draw! [pjs-obj f]
  (set! (.-draw pjs-obj) f)
  pjs-obj)

(defn height [pjs-obj]
  (.-height pjs-obj))

(defn width [pjs-obj]
  (.-width pjs-obj))

(defn color [pjs-obj val]
  (.color pjs-obj val))

(defn random [pjs-obj val]
  (.random pjs-obj val))

(defn mouse-x [pjs-obj]
  (.-mouseX pjs-obj))

(defn round [val]
  (js/Math.round val))

(defn load-image [pjs-obj path]
  (.loadImage pjs-obj path))

(defn limit-bounds [pjs-obj val r1 r2 r3 r4]
  (.map pjs-obj val r1 r2 r3 r4))

(defn mk-sketch [el-id draw-fn]
  (let [canvas (.getElementById js/document "main")
        pjs-obj (js/Processing. canvas draw-fn)]
    ;; Return a function to exit the sketch
    #(exit pjs-obj)))
