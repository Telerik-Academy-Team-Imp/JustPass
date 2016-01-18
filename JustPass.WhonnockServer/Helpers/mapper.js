(function() {
	'use strict';

	function mapDbAdviceModelToClientModel(adviceCollection) {
		let resultCollection = [];

		adviceCollection.forEach(advice => {
			resultCollection.push({
				Id: advice.Id,
				Text: advice.Text
			})
		});

		return resultCollection;
	}

	function mapDbBookModelToClientModel(booksCollection) {
		let resultCollection = [];

		booksCollection.forEach(book => {
			resultCollection.push({
				Id: book.Id,
				Rating: book.Rating,
				Sources: book.Sources,
				Title: book.Title
			})
		});

		return resultCollection;
	}

	function mapDbCommentModelToClientModel(commentsCollection) {
		let resultCollection = [];

		commentsCollection.forEach(comment => {
			resultCollection.push({
				Id: comment.Id,
				Text: comment.Text,
				Rating: comment.Rating,
				Owner: comment.Owner
			})
		});

		return resultCollection;
	}

	function mapDbCourseResultModelToClientModel(courseResultsCollection) {
		let resultCollection = [];

		courseResultsCollection.forEach(courseResult => {
			resultCollection.push({
				Id: courseResult.Id,
				Course: courseResult.Course,
				Result: courseResult.Result
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

		videosCollection.forEach(video => {
			resultCollection.push({
				Id: video.Id,
				Rating: video.Rating,
				Sources: video.Sources,
				Title: video.Title
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
