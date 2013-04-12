var sampleApp = angular.module('sampleApp',[]);
sampleApp.controller('SampleCtrl',function($scope, $http) {
	$scope.message = 'API Simple Showcase'
	$scope.items = []
	$scope.title = ''
	$scope.description = ''
	$scope.data = ''

	var getList = function(data) {
		return data.sampleListWrapper.samples;
	}
	//
	$http.get('api/samples').success(function(data){

		$scope.items = getList(data)
	});

	$scope.push = function(){
		var sampleVO = new Object();
		sampleVO.id = ""
		sampleVO.title = $scope.title
		sampleVO.description = $scope.description
		sampleVO.date = $scope.date
		console.log(JSON.stringify(sampleVO))
		var bean = '{"sampleVO":' + JSON.stringify(sampleVO) + '}'
		console.log(bean)
		$http({method: 'PUT', url: 'api/samples',  headers:{'Content-Type': 'application/json'}, data: bean})
			.success(function(data, status, headers, config){
				$scope.items = getList(data)
			})
			.error(function(data, status, headers, config){
				console.log('data ' + data)
				console.log('status ' + status)
				console.log('headers ' + headers)
			});

	}
})