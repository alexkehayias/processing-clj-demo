(ns ^:figwheel-always processing-cljs-demo.core)

(comment @pjs preload="img/test.jpg")

(enable-console-print!)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Version 1:                                                   ;;
;; - Almost a one for one copy of the js                        ;;
;; - Adds the ability to dynamically re-evaluate the draw loop  ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(def smallest 4)
(def largest 40)

;; (defn setup [pjs-obj]
;;   (.size pjs-obj 640 360)
;;   (.imageMode pjs-obj (.-CENTER pjs-obj))
;;   (.noStroke pjs-obj)
;;   (.background pjs-obj 255))

;; (defn draw
;;   "Returns a function that can be called with no arguments"
;;   [pjs-obj img]
;;   (let [size (.map pjs-obj (.-mouseX pjs-obj) 0 (.-width pjs-obj) smallest largest)
;;         x (js/Math.round (.random pjs-obj (.-width img)))
;;         y (js/Math.round  (.random pjs-obj (.-height img)))
;;         color (.color pjs-obj (.get img x y))]
;;     (.fill pjs-obj color 128)
;;     (.rect pjs-obj x y size size)))

;; (defn run [pjs-obj]
;;   (let [img (.loadImage pjs-obj "img/test.jpg")]
;;     (set! (.-setup pjs-obj) #(setup pjs-obj))
;;     (set! (.-draw pjs-obj) #(draw pjs-obj img))))

;; (defn sketch []
;;   (let [canvas (.getElementById js/document "main")
;;         pjs-obj (js/Processing. canvas run)]
;;     ;; Return a function to exit the sketch
;;     #(.exit pjs-obj)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Version 2:                                                  ;;
;; - Wrap processing object into something more functional     ;;
;; - Removes ugly interop code/syntax                          ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


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



(defn setup [pjs-obj]
  (-> pjs-obj
      (size 640 360)
      (image-mode "CENTER")
      (no-stroke)
      (background 255)))

(defn draw
  "Returns a function that can be called with no arguments"
  [pjs-obj img]
  (let [s (limit-bounds pjs-obj (mouse-x pjs-obj)
                        0 (width pjs-obj)
                        smallest largest)
        x (round (random pjs-obj (width img)))
        y (round (random pjs-obj (height img)))
        c (color pjs-obj (.get img x y))]
    (-> pjs-obj
        (fill c 128)
        (rect x y s s))))

(defn run [pjs-obj]
  (let [img (load-image pjs-obj "img/test.jpg")]
    (-> pjs-obj
        (setup! #(setup pjs-obj))
        (draw! #(draw pjs-obj img)))))

(defn sketch []
  (mk-sketch "main" run))

(set! (.-onload js/window) sketch)








