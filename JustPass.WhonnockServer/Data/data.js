(function()
{
	'use strict';

	const constants = require('./../helpers/constants');
	let everliveLocation = constants.EVERLIVE_LOCATION,
		Everlive = require('./../everlive.all.js'),
		el = new Everlive('9unql0fug2904yqd');
	//let myQuery = new everlive.Query();
	//myQuery.where().isin('Name', ['Doncho', 'Evlogi']);

	module.exports = {
		init: function()
		{
			return new Everlive(constants.EVERLIVE_API_KEY);
		},
		createQuery: function()
		{
			return new Everlive.Query();
		},
		data: el.data
	};
}());
