(function()
{

	'use strict';
	const constants = require('./Helpers/constants');
	let express = require('express'),
		bodyParser = require('body-parser');

	let app = express();

	app.use(bodyParser.json());

	let coursesRouter = require('./Routers/courses-router');
	coursesRouter(app);

	// On route "/api/logs" use router myRouter
	//app.use('/api/logs', myRouter);

	app.listen(constants.PORT, function()
	{
		console.log(`server running on port ${constants.PORT}`);
	});
}());
