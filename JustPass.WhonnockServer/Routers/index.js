module.exports =function (app) {
	// require('./advices-router.js')(app);
	// require('./books-router.js')(app);
	require('./comments-router.js')(app);
	// require('./external-courses-router.js')(app);
	require('./telerik-courses-router.js')(app);
	// require('./videos-router.js')(app);
};
