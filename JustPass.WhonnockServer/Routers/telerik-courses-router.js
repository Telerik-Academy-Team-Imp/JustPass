(function() {
	'use strict';

	console.log('Courses router loaded');

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		db = require('./../data/data.js').init(),
		myRouter = express.Router(),
		telerikCourseData = db.data('TelerikCourse');

	let logsIdGenerator = helpers.getNextId();

	myRouter
		.get('/', function(req, res) {
			telerikCourseData.get()
				.then(function(response) {
					let resultArray = [];
					response.result.forEach(x => {
						// Remove unnecessary stuff
						resultArray.push(x
							//{
							//    name: x.Name,
							//    id: x.Id,
							//    homeworks: x.Homeworks,
							//    endDate: x.EndDate,
							//    startDate: x.StartDate
							//}
						);
					});

					// Simulate slow response
					var millisecondsToWait = 5;
					setTimeout(function() {
						res.status(200).json({
							result: resultArray
						});
					}, millisecondsToWait);


				});
		})
		.get('/:id', function(req, res) {
			telerikCourseData.get()
				.then(function(response) {
					let resultArray = [];
					response.result.forEach(x => {
						resultArray.push({
							name: x.Name
						});
					});

					res.status(200)
						.json({
							result: resultArray.where(x => x.Id === req.Id)
						});
				});
		})
		.post('/', function(req, res) {
			let newCourse = {
				Name: req.body.name
			};

			telerikCourseData.create(newCourse).then(function(result) {
				res.status(201)
					.json({
						// 0_o -> wut I write?
						result: result.result
					});
			});
		});

	module.exports = function(app) {
		app.use('/api/telerik-courses', myRouter);
	};
}());
