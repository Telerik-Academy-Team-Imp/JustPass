(function() {
	'use strict';

	const currentRouter = 'books';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		mapper = require('./../helpers/mapper'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		currentTypeData = db.data('Book');

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
									.mapDbBookModelToClientModel(response)
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
		.get('/:title', function(req, res) {
			let query = new data.createQuery();

			query

				.where()
				.eq('Title', req.params.title)
				.done()
				.select(
					'Id',
					'Rating',
					'Sources',
					'Title');

			data
				.getAllWithQuery(currentTypeData, query)
				.then(function(response) {
						res
							.status(200)
							.json({
								result: mapper
									.mapDbBookModelToClientModel(response)
							});

						console.log(`get on ${currentRouter}:title successful`);
					},
					function(error) {
						console.log(`get on ${currentRouter}:title unsuccessful`);
						res.status(500).json({
							error: error
						});
					});
		})
		.post('/', function(req, res) {
			let newBook = {
				CreatedAt: new Date(),
				ModifiedAt: new Date(),
				CreatedBy: req.body.CreatedBy,
				ModifiedBy: req.body.ModifiedBy,
				Owner: req.body.Owner,
				Rating: req.body.Rating,
				Sources: req.body.Sources,
				Title: req.body.Title
			};

			currentTypeData
				.create(newBook)
				.then(function(response) {

						let temp = [];
						temp.push(response.result);

						res
							.status(201)
							.json({
								result: mapper
									.mapDbBookModelToClientModel(temp)
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
		app.use('/api/books', myRouter);
	};
}());
