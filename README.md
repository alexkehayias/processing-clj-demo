# ClojureScript April 2015 demo

## About

Demo of basic interop with external libraries via ClojureScript and Processing js.

- `processing-cljs-demo.direct-translation` is a line for line translation of `resources/js/demo2.js`
- `processing-cljs-demo.wrapped-version` uses a functional wrapper around processing.js to clean up syntax, add repl support, and easier composability

Check out the Quil library https://github.com/quil/quil/wiki/ClojureScript for a much more interesting wrapper/DSL of using processing in the browser.

## Usage

```
# Start the static file server and launch browser repl
lein figwheel

# Connect to the brepl from your browser
open http://localhost:3449
```

