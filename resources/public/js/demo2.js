// The next line is needed if running in JavaScript Mode with
// Processing.js
/* @pjs preload="img/test.jpg"; */

var demo2 = function(){
  var smallest = 4;
  var largest = 40;

  var sketch = function (pjs){
    var img = pjs.loadImage("img/test.jpg");

    pjs.setup = function () {
      pjs.size(640, 360);
      pjs.imageMode(pjs.CENTER);
      pjs.noStroke();
      pjs.background(255);
    };

    pjs.draw = function () {
      var size = pjs.map(pjs.mouseX, 0, pjs.width, smallest, largest);
      var x = Math.round(pjs.random(img.width));
      var y = Math.round(pjs.random(img.height));
      var color = pjs.color(img.get(x, y));
      pjs.fill(color, 128);
      pjs.rect(x, y, size, size);
    };
  }

  return function (){
    var canvas = document.getElementById("main");
    var p = new Processing(canvas, sketch);
    return p
  }
}

// window.onload = demo2()
