const http = require("http")
const fs = require("node:fs")

http.createServer((req, res) => {
    fs.readFile("./" + req.url, "utf8", (err, data) => {
        res.write(err ? err.toString() : data)
        res.end()
    })
}).listen(8080)