(function() {
	'use strict';

	const currentRouter = 'accounts';
	console.log(`${currentRouter} router loaded`);

	const constants = require('./../helpers/constants');
	let express = require('express'),
		helpers = require('./../helpers/helpers'),
		mapper = require('./../helpers/mapper'),
		data = require('./../data/data.js'),
		db = data.init(),
		myRouter = express.Router(),
		currentTypeData = db.data('Users');

	myRouter
	// (req, res, next)
		.put('/login', function(req, res) {
			let user = req.body,
				password = user.Password,
				username = user.Username;

			db.authentication.login(username, password, function(dataObj) {
				res
					.status(201)
					.json({
						result: "User logged in successfully",
						tokenInfo: dataObj
					});
			}, function(error) {
				res
					.status(404)
					.json({
						result: "User has not been logged in ",
						message: error.message
					});

				console.log(error);
			})
		})
		.put('/logout', function(req, res) {
			db.authentication.logout(function() {
				res
					.status(201)
					.json({
						result: "User logged out successfully"
					});
			}, function(error) {
				res
					.status(404)
					.json({
						result: "User has not been logged out ",
						message: error.message
					});

				console.log(error);
			})
		})
		.post('/register', function(req, res) {
			let newUser = req.body,
				password = newUser.Password,
				username = newUser.Username,
				otherAttributes = {
					Email: newUser.Email,
					DisplayName: newUser.DisplayName,
					CreatedAt: new Date(),
					ModifiedAt: new Date(),
					Courses: newUser.Courses,
					Image: newUser.Image
				};

			db.Users.register(username, password, otherAttributes,
				function(dataObj) {
					res
						.status(201)
						.json({
							result: "User created successfully"
						});
				},
				function(error) {
					res
						.status(404)
						.json({
							result: "User has not been created",
							message: error.message
						});

					console.log(error);
				})
		});

	module.exports = {
		controller: {},
		typeData: currentTypeData,
		init: function(app) {
			app.use('/api/auth', myRouter);
		}
	};
}());
