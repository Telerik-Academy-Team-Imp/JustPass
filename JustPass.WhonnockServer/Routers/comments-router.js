(function() {
	'use strict';

	const currentRouter = 'Comments';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		mapper = require('./../helpers/mapper'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		currentTypeData = db.data('Comment');

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
									.mapDbCommentModelToClientModel(response)
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
				.getAllWithQuery(currentTypeData, query)
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
			let newComment = {
				CreatedAt: new Date(),
				ModifiedAt: new Date(),
				CreatedBy: req.body.CreatedBy,
				ModifiedBy: req.body.ModifiedBy,
				Owner: req.body.Owner,
				Text: req.body.Text,
			};

			currentTypeData
				.create(newComment)
				.then(function(result) {
						res
							.status(201)
							.json({
								// 0_o -> wut I write?
								result: newComment
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
		app.use('/api/comments', myRouter);
	};
}());
