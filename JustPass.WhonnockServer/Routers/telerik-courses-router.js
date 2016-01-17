(function() {
	'use strict';

	const currentRouter = 'telerik-courses';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		mapper = require('./../helpers/mapper.js'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		currentTypeData = db.data('TelerikCourse');

	myRouter
	// (req, res, next)
		.get('/', function(req, res) {
			data
				.getAllWithQuery(currentTypeData)
				.then(function(response) {

						res
							.status(200)
							.json({
								result: mapper
									.mapDbTelerikCourseModelToClientModel(response)
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
				.getAllWithQuery(currentTypeData, query)
				.then(function(response) {

						res
							.status(200)
							.json({
								result: mapper
									.mapDbTelerikCourseModelToClientModel(response)
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

	module.exports = function(app) {
		app.use('/api/telerik-courses', myRouter);
	};
}());
