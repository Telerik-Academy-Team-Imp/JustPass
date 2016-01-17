(function ()
{
    'use strict';

    console.log('Courses router loaded');

    const constants = require('./../Helpers/constants');
    let express           = require('express'),
        helpers           = require('./../Helpers/helpers'),
        db                = require('./../Data/data.js').init(),
        myRouter          = express.Router(),
        telerikCourseData = db.data('TelerikCourse');

    let logsIdGenerator = helpers.getNextId();

    myRouter
        .get('/', function (req, res)
        {
            telerikCourseData.get()
                .then(function (response)
                {
                    let resultArray = [];
                    response.result.forEach(x =>
                    {
                        // Remove unnecessary stuff
                        resultArray.push(
                            {
                                name: x.Name,
                                id: x.Id,
                                homeworks: x.Homeworks,
                                endDate: x.EndDate,
                                startDate: x.StartDate
                            });
                    });

                    // Simulate slow response
                    var millisecondsToWait = 5;
                    setTimeout(function ()
                    {
                        res.status(200).json(
                            {
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
                        resultArray.push(
                            {
                                name: x.Name
                            });
                    });

                    res.status(200)
                        .json
                        ({
                            result: resultArray.where(x => x.Id === req.Id)
                        });
                });
        })
        .post('/', function (req, res)
        {
            let newCourse = {
                Name: req.body.sample
            };

            telerikCourseData.create(newCourse);

            res.status(201)
                .json
                ({
                    result: newCourse
                });
        });

    module.exports = function (app)
    {
        app.use('/api/courses', myRouter);
    };
}());
