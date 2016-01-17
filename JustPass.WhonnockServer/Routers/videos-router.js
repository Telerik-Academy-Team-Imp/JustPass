(function() {
	'use strict';

	let currentRouter = 'Videos';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		videoData = db.data('Video');

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
		.get('/:title', function(req, res) {
			let query = new data.createQuery();

			query
				.where()
				.eq('Title', req.params.title)
				.done()
				.select('Title', 'Sources', 'Id', 'Rating');

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
	.post('/', function(req, res) {
		let newVideo = {
			CreatedAt: new Date(),
			ModifiedAt: new Date(),
			CreatedBy: req.body.CreatedBy,
			ModifiedBy: req.body.ModifiedBy,
			Owner: req.body.Owner,
			Title: req.body.Title,
			Rating: req.body.Rating,
			Sources: req.body.Sources,
		};

		telerikCourseData
			.create(newVideo)
			.then(function(result) {
					res
						.status(201)
						.json({
							// 0_o -> wut I write?
							result: newVideo
						});

					console.log(`post on ${currentRouter} unsuccessful`);
				},
				// TODO: HANDLE THIS
				function(error) {
					res
						.status(500)
						.json({
							error: error
						});

					console.log(`post on ${currentRouter} unsuccessful`);
				});
	});

	module.exports = function(app) {
		app.use('/api/videos', myRouter);
	};
}());
