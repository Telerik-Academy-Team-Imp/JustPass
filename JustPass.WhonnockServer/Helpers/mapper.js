(function() {
	'use strict';

	function mapDbAdviceModelToClientModel(adviceCollection) {
		let resultCollection = [];

		adviceCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Text: course.Text
			})
		});

		return resultCollection;
	}

	function mapDbBookModelToClientModel(booksCollection) {
		let resultCollection = [];

		booksCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Rating: course.Rating,
				Sources: course.Sources,
				Title: course.Title
			})
		});

		return resultCollection;
	}

	function mapDbCommentModelToClientModel(commentsCollection) {
		let resultCollection = [];

		commentsCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Text: course.Text,
				Rating: course.Rating,
				Owner: course.Owner
			})
		});

		return resultCollection;
	}

	function mapDbCourseResultModelToClientModel(courseResultsCollection) {
		let resultCollection = [];

		courseResultsCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Course: course.Course,
				Result: course.Result
			})
		});

		return resultCollection;
	}

	function mapDbExternalCourseModelToClientModel(externalCoursesCollection) {
		let resultCollection = [];

		externalCoursesCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Organisation: course.Organisation,
				Source: course.Source,
				Subject: course.Subject,
				Title: course.Title
			})
		});

		return resultCollection;
	}

	function mapDbTelerikCourseModelToClientModel(telerikCoursesCollection) {
		let resultCollection = [];

		telerikCoursesCollection.forEach(course => {
			resultCollection.push({
				UsefulAdvice: course.UsefulAdvice,
				StartDate: course.StartDate,
				SimilarCourses: course.SimilarCourses,
				Name: course.Name,
				MaxPossibleResult: course.MaxPossibleResult,
				HomeworksCount: course.HomeworksCount,
				HelpfulVideos: course.HelpfulVideos,
				HelpfulBooks: course.HelpfulBooks,
				EndDate: course.EndDate,
				Difficulty: course.Difficulty,
				Comments: course.Comments,
				Id: course.Id
			})
		});

		return resultCollection;
	}

	function mapDbVideoModelToClientModel(videosCollection) {
		let resultCollection = [];

		videosCollection.forEach(course => {
			resultCollection.push({
				Id: course.Id,
				Rating: course.Rating,
				Sources: course.Sources,
				Title: course.Title
			})
		});

		return resultCollection;
	}

	module.exports = {
		mapDbVideoModelToClientModel,
		mapDbTelerikCourseModelToClientModel,
		mapDbExternalCourseModelToClientModel,
		mapDbCourseResultModelToClientModel,
		mapDbCommentModelToClientModel,
		mapDbBookModelToClientModel,
		mapDbAdviceModelToClientModel
	}
}());
