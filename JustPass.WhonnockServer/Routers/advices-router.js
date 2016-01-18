(function() {
	'use strict';

	const currentRouter = 'advice';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		mapper = require('./../helpers/mapper'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		currentTypeData = db.data('Advice');

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
									.mapDbAdviceModelToClientModel(response)
							});

						console.log(`get on ${currentRouter} successful`);
					},
					function(error) {
						console.log(`get on ${currentRouter} unsuccessful`);
						res.status(500).json({
							error
						});
					});
		})
		.post('/', function(req, res) {
			let newAdvice = {
				CreatedAt: new Date(),
				ModifiedAt: new Date(),
				CreatedBy: req.body.CreatedBy,
				ModifiedBy: req.body.ModifiedBy,
				Owner: req.body.Owner,
				Text: req.body.Text
			};

			currentTypeData
				.create(newAdvice)
				.then(function(response) {

						let temp = [];
						temp.push(response.result);

						res
							.status(201)
							.json({
								result: mapper
									.mapDbAdviceModelToClientModel(temp)
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

	module.exports = {
		controller: {},
		typeData: currentTypeData,
		init: function (app) {
			app.use('/api/advice', myRouter);
		}
	};
}());
