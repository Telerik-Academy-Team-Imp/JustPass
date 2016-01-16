"use strict";
console.log('Logs router loaded');

const constants = require('./../Helpers/constants');
let Everlive = require(constants.everliveLocation),
    express  = require('express'),
    myRouter = express.Router();

let el = new Everlive('9unql0fug2904yqd');
let myQuery = new Everlive.Query();
myQuery.where().isin('Name', ['Doncho', 'Evlogi']);

// Type
let telerikCourseData = el.data('TelerikCourse');
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
        telerikCourseData.get()
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
                setTimeout(function ()
                {
                    res.status(201).json({
                        result: resultArray
                    });
                }, millisecondsToWait);


            });
    })
    .get('/:id', function (req, res)
    {
        telerikCourseData.get()
            .then(function (response)
            {
                let resultArray = [];
                response.result.forEach(x =>
                {
                    resultArray.push({
                        name: x.Name
                    });
                });

                res.status(201)
                    .json({
                        result: resultArray.where(x => x.Id === req.Id)
                    });
            });
    })
    .post('/', function (req, res)
    {
        let strauss = {
            Name: "Trsa"
        };

        telerikCourseData.create(strauss);

        res
            .status(201)
            .json({
                result: strauss
            });
    });

module.exports = function (app)
{
    app.use('/api/courses', myRouter);
};