"use strict";

let express    = require('express'),
    bodyParser = require('body-parser');

let app = express();

app.use(bodyParser.json());

let logsRouter = require('./Routers/logs-router');
logsRouter(app);

// On route "/api/log" use router myRouter
//app.use('/api/logs', myRouter);

let port = 3001;


app.listen(port, function ()
{
    console.log(`server running on port ${port}`);
});