(function() {
	'use strict';

	let currentRouter = 'Advice';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		adviceData = db.data('Advice');

	myRouter
	// (req, res, next)
		.get('/', function(req, res) {
			data
				.getAllWithQuery(adviceData)
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
		.get('/:owner', function(req, res) {
			let query = new data.createQuery();

			query
				.where()
				.eq('Owner', req.params.owner)
				.done()
				.select('Text', 'Owner', 'Id');

			data
				.getAllWithQuery(adviceData, query)
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

						console.log(`get on ${currentRouter}:owner successful`);
					},
					// TODO: HANDLE THIS
					function(error) {
						console.log(`get on ${currentRouter}:owner unsuccessful`);
						res.status(500).json({
							error: error
						});
					});
		})
	.post('/', function(req, res) {
		let newCourse = {
			CreatedAt: new Date(),
			ModifiedAt: new Date(),
			CreatedBy: req.body.CreatedBy,
			ModifiedBy: req.body.ModifiedBy,
			Owner: req.body.Owner,
			Text: req.body.Text,
		};

		telerikCourseData
			.create(newCourse)
			.then(function(result) {
					res
						.status(201)
						.json({
							// 0_o -> wut I write?
							result: newCourse
						});

					console.log(`post on ${currentRouter} unsuccessful`);
				},
				// TODO: HANDLE THIS
				function(error) {
					res.status(500).json({
						error: error
					});

					console.log(`post on ${currentRouter} unsuccessful`);
				});
	});

	module.exports = function(app) {
		app.use('/api/advice', myRouter);
	};
}());
