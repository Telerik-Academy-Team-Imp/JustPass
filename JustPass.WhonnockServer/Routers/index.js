(function() {
	'use strict';

	const constants = require('./../helpers/constants.js');
	let fileSystem = require('fs');

	module.exports = function(app) {
		fileSystem
			.readdirSync(constants.ROUTERS_LOCATION)
			.filter(file => file == 'comments-router.js')
			// .filter(file => file != 'index.js')
			.forEach(file => require(`./${file}`)(app));

		// // require('./advices-router.js')(app);
		// // require('./books-route\r.js')(app);
		// require('./comments-router.js')(app);
		// // require('./external-courses-router.js')(app);
		// require('./telerik-courses-router.js')(app);
		// // require('./videos-router.js')(app);
	};
}());
