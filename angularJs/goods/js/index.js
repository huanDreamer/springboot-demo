var baseurl = "http://localhost:8080/";
// 记录 alert 的内容
var alerts = [];
// 要删除的商品 id
var _id = -1;

// 添加 alert 消息提醒
function addAlert($rootScope, $interval, type, msg) {

    // 添加 alert
    alerts.push({type: type, msg: msg});

    $rootScope.alerts = alerts;

    $rootScope.closeAlert = function (index) {
        // splice 函数表示从 index 位置开始删除 1 个，后面的往前推
        $rootScope.alerts.splice(index, 1);
        alerts = $rootScope.alerts;
    };


    // 定时询问 alerts 的内容
    $interval(function () {
        $rootScope.alerts = alerts;
    }, 1500);

}

// 对 alerts 里面的内容进行每1.5秒清除一个, 这样可以实现每次都只清除一个
setInterval(function () {
    if (alerts.length != 0) {
        alerts.splice(0, 1);
    }
}, 1500);


angular.module('myApp', [
    'ngRoute',
    "ngResource",
    "ngStorage",
    'ui.router',
    'ngAnimate',
    'ngSanitize',
    'ui.bootstrap'
])

    .config(['$routeProvider', '$stateProvider', function ($routeProvider, $stateProvider) {
        $routeProvider
            .when('/', {templateUrl: 'list.html'})
            .when('/add', {templateUrl: 'add.html'})
            .when('/update/:id', {templateUrl: 'update.html'})
            .otherwise({redirectTo: '/'});

        $stateProvider
            .state('updateGoods', {
                url: '/update/:id',
                templateUrl: 'update.html',
                controller: 'updateController'
            });
    }])

    // 商品的服务
    .service('GoodsService', function ($resource) {

        //获取列表
        this.listAll = function () {

            var deviceres = $resource(baseurl + 'listAll');

            return deviceres.get();
        };

        // 根据 id 获取商品
        this.getGoods = function (id) {

            var deviceres = $resource(baseurl + 'getGoods/:id');

            return deviceres.get({id: id});
        };

        //添加
        this.insert = function (goods) {
            var deviceres = $resource(baseurl + 'insert');

            return deviceres.save(goods);
        };

        // 删除
        this.delete = function (id) {

            var deviceres = $resource(baseurl + 'delete/:id');

            return deviceres.remove({id: id});
        };

        // 修改
        this.update = function (goods) {
            var deviceres = $resource(baseurl + 'update');

            return deviceres.save(goods);
        };
    })


    // 显示以及删除商品
    .controller('listController', function ($scope, $rootScope, $interval, $uibModal, GoodsService) {

        // 刷新商品列表

        GoodsService.listAll().$promise.then(function (data) {
            if (data.code == 0) {
                $scope.goods = data.data;
            }
        });



        // $scope.goods = [{"id":1,"name":"铅笔","price":1.5,"date":1474905600000,"num":10},{"id":2,"name":"笔记本","price":5.0,"date":1474905600000,"num":30},{"id":3,"name":"草稿纸","price":1.0,"date":1473955200000,"num":100}];

        // 打开对话框
        $scope.open = function (id) {
            var modalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'myConfirm.html',
                controller: 'deleteController', // 设置处理对话框的 controller
                size: "sm"
            });

            _id = id;
            console.log(_id);
        };

    })


    // 删除商品的 controller
    .controller('deleteController', function ($uibModalInstance, $scope, $rootScope, $interval, $route, $state, GoodsService) {
        //var $ctrl = this;

        $scope.delete = function () {
            console.log("ok");
            GoodsService.delete(_id).$promise.then(function (data) {

                    if (data.code == 0) {
                        addAlert($rootScope, $interval, 'success', '已删除');
                        $route.reload();

                    } else {
                        addAlert($rootScope, $interval, 'danger', '删除失败');
                    }

                });
            $uibModalInstance.dismiss('cancel');
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    })



    // 添加商品
    .controller('addController', function ($scope, $rootScope, $interval, $location, GoodsService) {

        $scope.save = function () {
            GoodsService.insert($scope.goods).$promise.then(function (data) {
                if (data.code == 0) {
                    addAlert($rootScope, $interval, 'success', '保存成功');
                    $location.path("#/");
                } else if (data.code != 0 && data.msg == 'fail') {
                    addAlert($rootScope, $interval, 'danger', '保存失败,请检查id是否已经存在');
                } else {
                    addAlert($rootScope, $interval, 'danger', data.msg);
                }
            })
        };
    })

    // 修改商品
    .controller('updateController', function ($scope, $rootScope, $interval, GoodsService, $stateParams, $location, $filter) {


        // alert($stateParams.id);
        // 获取数据 并显示到输入框
        //$scope.goods
        GoodsService.getGoods($stateParams.id).$promise.then(function (data) {

            if (data.code == 0) {
                // 日期格式转换(时间戳->Date)
                data.data.date=  new Date($filter('date')(data.data.date,'yyyy-MM-dd'));
                $scope.goods = data.data;
            }

        });

        // 保存修改
        $scope.save = function () {

            GoodsService.update($scope.goods).$promise.then(function (data) {
                if (data.code == 0) {
                    addAlert($rootScope, $interval, 'success', '修改成功');
                    $location.path("#/");
                } else if (data.code != 0 && data.msg == 'fail') {
                    addAlert($rootScope, $interval, 'danger', '修改失败');
                } else {
                    addAlert($rootScope, $interval, 'danger', data.msg);
                }
            })

        };
    })


;