"use strict";

console.log('Logs router loaded');

let express = require('express'),
    myRouter = express.Router(),
    logs = [];

let Everlive = require('./../everlive.all');
//<script src="everlive.all.js" charset="utf-8"></script>

function* getNextId() {
    let id = 1;
    while (true) {
        yield id++;
    }
}

let logsIdGenerator = getNextId();

myRouter
    .get('/', function (req, res) {
        let el = new Everlive('9unql0fug2904yqd');

        let telerikTrainerData = el.data('TelerikCourse');

        let myQuery = new Everlive.Query();
        myQuery.where().isin('Name', ['Doncho', 'Evlogi']);
        telerikTrainerData.get()
            .then(function (response) {
                let resultArray = [];
                response.result.forEach(x => {
                    resultArray.push({
                        name: x.Name
                    });
                });

                res.status(201).json({
                    result: resultArray
                });
            });

    })
    .get('/:id', function (req, res) {


    })
    .post('/', function (req, res) {
        let log = req.body;
        log.id = logsIdGenerator.next();
        log.date = new Date();
        logs.push(log);

        res
            .status(201)
            .json
            ({
                result: log
            });
    });

module.exports = function (app) {
    app.use('/api/logs', myRouter);
};