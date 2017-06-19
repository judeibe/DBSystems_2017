/**
 * Created by judetibe on 5/4/17.
 */
var vendingApp = angular.module('vendingApp', ['ui.bootstrap']);
vendingApp.controller('vendingCtrl', function ($scope, $http, $window) {

    $scope.vendInit = function () {

        $scope.info = "";
        $scope.count = 0;
        $scope.dollars = 0;
        $scope.quarters = 0;
        $scope.dimes = 0;
        $scope.nickels = 0;
        $scope.cDollars = 0;
        $scope.cQuarters = 0;
        $scope.cDimes = 0;
        $scope.cNickels = 0;
    };


    $scope.refreshScreen = function () {
        $window.location.reload()
    };


    $scope.addStock = function (productId) {
        $http.put("/vending-machine/product/" + productId + "/addstock")
            .success(function () {
                $scope.refreshScreen()
            });
    };

    $scope.removeStock = function (productId) {
        $http.put("/vending-machine/product/" + productId + "/removestock")
            .success(function () {
                $scope.refreshScreen()
            });
    };


    $scope.addDollar = function () {
        if ($scope.dollars != 2) {
            $scope.count = $scope.count + 1.0;
            $scope.dollars = $scope.dollars + 1;
        }
        ;
    };

    $scope.addQuarter = function () {
        $scope.count = $scope.count + .25;
        $scope.quarters = $scope.quarters + 1;
    };

    $scope.addDime = function () {
        $scope.count = $scope.count + .10;
        $scope.dimes = $scope.dimes + 1;
    };

    $scope.addNickel = function () {
        $scope.count = $scope.count + .05;
        $scope.nickels = $scope.nickels + 1;
    };

    $scope.$watch('count', function (value) {
        $scope.info = parseFloat(Math.round(value * 100) / 100).toFixed(2);
    });

    $scope.changePrice = function (productId) {
        var model = 'price' + productId;
        $http.put("/vending-machine/product/" + productId + "/changeprice/" + $scope.$eval(model))
            .success(function () {
                $scope.refreshScreen()
            });
    };

    $scope.purchase = function (productId) {
        $scope.count = 0;
        var data = $.param({
            dollars: $scope.dollars,
            quarters: $scope.quarters,
            dimes: $scope.dimes,
            nickels: $scope.nickels,
            productId: productId
        });

        var config = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        };
        var flag = false;
        $http.get('/vending-machine/product/' + productId + '/getstock/')
            .success(function (data) {
                if (data == 0) {
                    $scope.info = "OUT OF STOCK";
                }
            });


        $http.post('/vending-machine/purchase', data, config)
            .success(function (data) {
                console.log(data)
                var jsonData = JSON.stringify(data);
                var javascriptObject = JSON.parse(jsonData);
                Object.keys(javascriptObject).forEach(function (key) {
                    switch (javascriptObject[key].denomination) {
                        case 'DOLLAR':
                            $scope.cDollars = javascriptObject[key].count;
                            break;
                        case 'QUARTER':
                            $scope.cQuarters = javascriptObject[key].count;
                            break;
                        case 'DIME':
                            $scope.cDimes = javascriptObject[key].count;
                            break;
                        case 'NICKEL':
                            $scope.cNickels = javascriptObject[key].count;
                            break;
                    }
                    ;
                })
            });
    };


});