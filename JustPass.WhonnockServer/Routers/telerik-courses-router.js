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
			telerikCourseData
				.get()
				.then(function(response) {
						let resultArray = [];
						response
							.result
							.forEach(x => {
								// Remove unnecessary stuff
								resultArray
									.push(x
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
							res
								.status(200)
								.json({
									result: resultArray
								});
						}, millisecondsToWait);


					},
					// TODO: HANDLE THIS
					function(error) {
						res.status(404);
					});
		})
		.get('/:id', function(req, res) {
			telerikCourseData
				.get()
				.then(function(response) {
						let resultArray = [];
						response
							.result
							.forEach(x => {
								resultArray
									.push({
										name: x.Name
									});
							});

						res
							.status(200)
							.json({
								result: resultArray.where(x => x.Id === req.Id)
							});
					},
					// TODO: HANDLE THIS
					function(error) {
						res.status(404);
					});
		})
		.post('/', function(req, res) {
			let newCourse = {
				Id: req.body.Name,
				CreatedAt: new Date(),
				ModifiedAt: new Date(),
				CreatedBy: req.body.CreatedBy,
				ModifiedBy: req.body.ModifiedBy,
				Owner: req.body.Owner,
				Comments: req.body.Comments,
				Difficulty: req.body.Difficulty,
				EndDate: req.body.EndDate,
				HelpfulBooks: req.body.HelpfulBooks,
				HelpfulVideos: req.body.HelpfulVideos,
				HomeworksCount: req.body.HomeworksCount,
				Name: req.body.Name,
				SimilarCourses: req.body.SimilarCourses,
				StartDate: req.body.StartDate,
				UsefulAdvice: req.body.UsefulAdvice
			};

			console.log('Post started');

			telerikCourseData
				.create(newCourse)
				.then(function(result) {
						res
							.status(201)
							.json({
								// 0_o -> wut I write?
								result: result.result,
								request: req.body,
								objectish: newCourse
							});

						console.log('Post successful');
					},
					// TODO: HANDLE THIS
					function(error) {
						res.status(404).json(error);

						console.log('Post unsuccessful');
					});
		});

	module.exports = function(app) {
		app.use('/api/telerik-courses', myRouter);
	};
}());
