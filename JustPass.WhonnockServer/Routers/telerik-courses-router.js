(function() {
	'use strict';

	let currentRouter = 'telerik-courses';
	console.log(`${currentRouter} advices router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		telerikCourseData = db.data('TelerikCourse');

	myRouter
	// (req, res, next)
		.get('/', function(req, res) {
			data
				.getAllWithQuery(telerikCourseData)
				.then(function(response) {
						let resultArray = [];

						response
							.forEach(x => {
								resultArray
									.push({
										name: x.Name
									});
							});

						res
							.status(200)
							.json({
								result: resultArray
							});

						console.log(`get on ${currentRouter} successful`);
					},
					// TODO: HANDLE THIS
					function(error) {
						console.log(`get on ${currentRouter} unsuccessful`);
						res.status(500).json({
							error
						});
					});
		})
		.get('/:title', function(req, res) {
			let query = new data.createQuery();

			query
				.where()
				.eq('Name', req.params.title)
				.done()
				.select('Name', 'Difficulty');

			data
				.getAllWithQuery(telerikCourseData, query)
				.then(function(response) {
						let resultArray = [];

						response
							.forEach(x => {
								resultArray
									.push(x);
							});

						res
							.status(200)
							.json({
								result: resultArray
							});

						console.log(`get on ${currentRouter}:title successful`);
					},
					// TODO: HANDLE THIS
					function(error) {
						console.log(`get on ${currentRouter}:title unsuccessful`);
						res.status(500).json({
							error: error
						});
					});
		});
	// .post('/', function(req, res) {
	// 	let newCourse = {
	// 		CreatedAt: new Date(),
	// 		ModifiedAt: new Date(),
	// 		CreatedBy: req.body.CreatedBy,
	// 		ModifiedBy: req.body.ModifiedBy,
	// 		Owner: req.body.Owner,
	// 		Comments: req.body.Comments,
	// 		Difficulty: req.body.Difficulty,
	// 		EndDate: req.body.EndDate,
	// 		HelpfulBooks: req.body.HelpfulBooks,
	// 		HelpfulVideos: req.body.HelpfulVideos,
	// 		HomeworksCount: req.body.HomeworksCount,
	// 		Name: req.body.Name,
	// 		SimilarCourses: req.body.SimilarCourses,
	// 		StartDate: req.body.StartDate,
	// 		UsefulAdvice: req.body.UsefulAdvice
	// 	};
	//
	// 	telerikCourseData
	// 		.create(newCourse)
	// 		.then(function(result) {
	// 				res
	// 					.status(201)
	// 					.json({
	// 						// 0_o -> wut I write?
	// 						result: result.result,
	// 						request: req.body,
	// 						objectish: newCourse
	// 					});
	//
	// 				console.log('Post successful');
	// 			},
	// 			// TODO: HANDLE THIS
	// 			function(error) {
	// 				res.status(404).json(error);
	//
	// 				console.log('Post unsuccessful');
	// 			});
	// });

	module.exports = function(app) {
		app.use('/api/telerik-courses', myRouter);
	};
}());
