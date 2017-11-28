// requires Node's `http` module
var http = require('http');

// Declares a function that gets invoked on every request
function arbitraryFunction(req, res) {
  res.setHeader("Content-Type", "text/plain");
  res.end("Cho, I'll take two packets of sugar.");
}

// Creates an instance of a server with our callback
var server = http.createServer(arbitraryFunction);

// Binds our server to a port, host, and then logs a message
server.listen(8080, 'localhost' function() {
  console.log("Listening on port 8080");
});
