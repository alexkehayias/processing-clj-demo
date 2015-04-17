(ns ^:figwheel-always processing-cljs-demo.direct-translation)

(comment @pjs preload="img/test.jpg")

(enable-console-print!)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Version 1:                                                   ;;
;; - Almost a one for one copy of resources/js/demo2.js         ;;
;; - Adds the ability to dynamically re-evaluate the draw loop  ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 

(def smallest 4)
(def largest 40)

(defn setup [pjs-obj]
  (.size pjs-obj 640 360)
  (.imageMode pjs-obj (.-CENTER pjs-obj))
  (.noStroke pjs-obj)
  (.background pjs-obj 255))

(defn draw
  "Returns a function that can be called with no arguments"
  [pjs-obj img]
  (let [size (.map pjs-obj (.-mouseX pjs-obj) 0 (.-width pjs-obj) largest smallest)
        x (js/Math.round (.random pjs-obj (.-width img)))
        y (js/Math.round  (.random pjs-obj (.-height img)))
        color (.color pjs-obj (.get img x y))]
    (.fill pjs-obj color 128)
    (.rect pjs-obj x y size size)))

(defn run [pjs-obj]
  (let [img (.loadImage pjs-obj "img/test.jpg")]
    (set! (.-setup pjs-obj) #(setup pjs-obj))
    (set! (.-draw pjs-obj) #(draw pjs-obj img))))

(defn sketch []
  (let [canvas (.getElementById js/document "main")
        pjs-obj (js/Processing. canvas run)]
    ;; Return a function to exit the sketch
    #(.exit pjs-obj)))

(set! (.-onload js/window) sketch)

