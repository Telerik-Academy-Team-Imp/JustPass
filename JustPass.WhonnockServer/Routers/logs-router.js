"use strict";
console.log('Logs router loaded');

let express  = require('express'),
    myRouter = express.Router(),
    logs     = [];

let Everlive = require('./../everlive.all');
let el = new Everlive('9unql0fug2904yqd');
let myQuery = new Everlive.Query();
myQuery.where().isin('Name', ['Doncho', 'Evlogi']);
let telerikTrainerData = el.data('TelerikCourse');
//<script src="everlive.all.js" charset="utf-8"></script>

function* getNextId()
{
    let id = 1;
    while (true)
    {
        yield id++;
    }
}

let logsIdGenerator = getNextId();

myRouter
    .get('/', function (req, res)
    {
        telerikTrainerData.get()
            .then(function (response)
            {
                let resultArray = [];
                response.result.forEach(x =>
                {
                    resultArray.push({
                        name: x.Name,
                        id: x.Id,
                        homeworks: x.Homeworks,
                        endDate: x.EndDate,
                        startDate: x.StartDate
                        //x
                    });
                });

                var millisecondsToWait = 5000;
                setTimeout(function() {
                    res.status(201).json({
                        result: resultArray
                    });
                }, millisecondsToWait);


            });
    })
    .get('/:id', function (req, res)
    {
        telerikTrainerData.get()
            .then(function (response)
            {
                let resultArray = [];
                response.result.forEach(x =>
                {
                    resultArray.push({
                        name: x.Name
                    });
                });

                res.status(201).json({
                    result: resultArray.where(x => x.Id === req.Id)
                });
            });
    })
    .post('/', function (req, res)
    {

        let telerikTrainerData = el.data('TelerikCourse');
        telerikTrainerData.pos
        let log = req.body;
        log.id = logsIdGenerator.next();
        log.date = new Date();
        logs.push(log);

        res
            .status(201)
            .json({
                result: log
            });
    });

module.exports = function (app)
{
    app.use('/api/courses', myRouter);
};