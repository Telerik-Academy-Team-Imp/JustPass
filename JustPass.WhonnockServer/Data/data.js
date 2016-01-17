(function() {
	'use strict';

	const constants = require('./../helpers/constants');
	let everliveLocation = constants.EVERLIVE_LOCATION,
		Everlive = require('./../everlive.all.js'),
		el = new Everlive('9unql0fug2904yqd');
	//let myQuery = new everlive.Query();
	//myQuery.where().isin('Name', ['Doncho', 'Evlogi']);

	let telerikCourseData = el.data('TelerikCourse');

	function getAllWithQuery(typeData,query) {
		return new Promise((resolve, reject) => {
			typeData
				.get(query)
				.then(function(response) {
						resolve(response.result);
					},
					function(error) {
						reject(error);
					});
		});
	}

	module.exports = {
		init: function() {
			return el;
		},
		createQuery: function() {
			return new Everlive.Query();
		},
		data: el.data,
		getAllWithQuery: getAllWithQuery
	};
}());
