(function() {
	'use strict';

	function* getNextId() {
		let id = 1;
		while (true) {
			yield id++;
		}
	}

	module.exports = {
		getNextId
	};
}());
