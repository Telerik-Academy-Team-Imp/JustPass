"use strict";

let express    = require('express'),
    constants  = require('./Helpers/constants'),
    bodyParser = require('body-parser');

let app = express();

app.use(bodyParser.json());

let logsRouter = require('./Routers/logs-router');
logsRouter(app);

// On route "/api/log" use router myRouter
//app.use('/api/logs', myRouter);

app.listen(constants.port, function ()
{
    console.log(`server running on port ${constants.port}`);
});