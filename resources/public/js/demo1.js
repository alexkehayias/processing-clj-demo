// https://processing.org/examples/graphing2dequation.html

// Simple way to attach js code to the canvas is by using a function
function sketchProc(processing) {

  console.log('drawing!!');
  processing.setup = function() {
    processing.size(640, 360);
    processing.frameRate(30);
  };
  
  // Override draw function, by default it will be called 60 times per
  // second
  processing.draw = function() {
    processing.loadPixels();
    var n = (processing.mouseX * 10.0) / processing.width;
    var w = 16.0;          // 2D space width
    var h = 16.0;          // 2D space height
    var dx = w / processing.width;    // Increment x this amount per pixel
    var dy = h / processing.height;   // Increment y this amount per pixel
    var x = -w/2;          // Start x at -1 * width / 2

    for (var i = 0; i < processing.width; i++) {
      var y = -h/2;        // Start y at -1 * height / 2
      for (var j = 0; j < processing.height; j++) {
        // Convert cartesian to polar
        var r = processing.sqrt((x * x) + (y * y));    
        // Convert cartesian to polar
        var theta = processing.atan2(y, x);
        // Compute 2D polar coordinate function
        // Results in a value between -1 and 1
        var val = processing.sin(n*processing.cos(r) + 5 * theta);
        // Map resulting vale to grayscale value 0 - 255
        var c = processing.color((val + 1.0) * (255.0/2.0));
        processing.pixels.setPixel(i + j * processing.width, c);

        y += dy;                // Increment y
      }
      x += dx;                  // Increment x
    };
    processing.updatePixels();
  };
}

// attaching the sketchProc function to the canvas

function run(){
  var canvas = document.getElementById("main");
  var p = new Processing(canvas, sketchProc);
  return p
}

window.onload = run
// p.exit(); to detach it
