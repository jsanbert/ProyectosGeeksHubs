const express = require("express");
const bodyParser = require('body-parser');
const fs = require('fs');

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded( { extended: true } ));

const PROTOCOL = "http";
const SERVER = "localhost";
const PORT = 8080;

const data = {
    "characters": PROTOCOL + "://" + SERVER + ":" + PORT + "/api/characters",
    "locations": PROTOCOL + "://" + SERVER + ":" + PORT + "/api/locations",
    "episodes": PROTOCOL + "://" + SERVER + ":" + PORT + "/api/episodes" 
}

app.get("/", function(req, res, next) {
    res.send("Access " + PROTOCOL  + "://" + SERVER + ":" + PORT + "/api route to get the API");
    next();
});

app.get("/api", function(req, res, next) {
    res.send(JSON.stringify(data, null, 4));
    next();
});

app.get("/api/characters", function(req, res, next) {
    fs.readFile('./db/character.json', 'utf8', function(err, data) {
        if(err)
            console.log(err);

        res.send(JSON.parse(data));
    });
});

app.get("/api/locations", function(req, res, next) {
    fs.readFile('./db/locations.json', 'utf8', function(err, data) {
        if(err)
            console.log(err);

        res.send(JSON.parse(data));
    });
});

app.get("/api/episodes", function(req, res, next) {
    fs.readFile('./db/episodes.json', 'utf8', function(err, data) {
        if(err)
            console.log(err);

        res.send(JSON.parse(data));
    });
});

app.use(function(req, res, next) {
    res.status(404);
    res.send('404: Not Found');
});

app.listen(PORT, function() {
    console.log("API listening on port", PORT);
});