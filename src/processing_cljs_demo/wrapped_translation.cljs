(ns ^:figwheel-always processing-cljs-demo.wrapped-translation
    (:require [processing-cljs-demo.processing :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Version 2:                                                  ;;
;; - Wrap processing object into something more functional     ;;
;; - Removes ugly interop code/syntax                          ;;
;; - Processing calls are now composable and return values     ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn setup [pjs-obj]
  (-> pjs-obj
      (size 640 360)
      (image-mode "CENTER")
      (no-stroke)
      (background 255)))

(defn draw
  "Returns a function that can be called with no arguments"
  [pjs-obj img]
  (let [
        s (limit-bounds pjs-obj (mouse-x pjs-obj)
                        0 (width pjs-obj)
                        4 40)
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
