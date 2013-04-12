var appModule = angular.module('appModule',['ngResource']);
var appCtrl = appModule.controller('appCtrl',function($scope,$resource,$http){
	$scope.mainTitle = 'PARAPHEUR-WS'
	$scope.subTitle = 'Parapheur webservices module show case'
	$scope.documentName = ''
	$scope.documentlabel = ''

	$scope.documents = []
	$scope.notes = []
	$scope.metas = []
	$scope.attachments = []
	$scope.actions = []
	$scope.history = []


	$http.get('api/documents').success(function(data){
		console.log(data);
		$scope.documents = data
	}).error(function() {
		console.log('error !')
	})

	$scope.getAttachmentContent = function(attachmentId) {
		$http.get('api/documents/content/' + attachmentId).success(function(data){
			console.log(data)
		}).error(function(data){
			console.log(data)
		})
	}

	$scope.load = function(document) {
		
		//
		$scope.documentName = document.filename
		$scope.documentlabel = document.label

		// load notes
		$http.get('api/documents/notes/' + document.id).success(function(data){
			$scope.notes = data
		}).error(function(data){
			console.log(data)
		})

		// load metas
		$http.get('api/documents/metas/' + document.id).success(function(data){
			$scope.metas = data
		}).error(function(data){
			console.log(data)
		})

		// load attachments
		$http.get('api/documents/attachments/' + document.id).success(function(data){
			$scope.attachments = data
		}).error(function(data){
			console.log(data)
		})

		// load actions
		$http.get('api/documents/actions/' + document.id).success(function(data){
			$scope.actions = data
		}).error(function(data){
			console.log(data)
		})

		// content
		$http.get('api/documents/content/' + document.id).success(function(data){
			
		}).error(function(data){
			console.log(data)
		})

	}
})