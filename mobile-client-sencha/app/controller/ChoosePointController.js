Ext.define('mobile-client-sencha.controller.ChoosePointController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.MapView',
			'mobile-client-sencha.view.MainNavView'
		],

		refs: {

			choosePointMenu: 'list[id=choosePointMenu]',

			routesView: {
				autoCreate: true,
				selector: '[id=routesView]',
				xtype: 'RoutesView'
			},

			choosePointView: {
				autoCreate: true,
				selector: '[id=choosePointView]',
				xtype: 'ChoosePointView'
			},

			mapView: {
				autoCreate: true,
				selector: '[id=mapView]',
				xtype: 'MapView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {

			choosePointMenu: {
				itemtap: function (list, idx, target, record, evt) {
					setTimeout(function() {
						list.deselect(idx);
					}, 100);
					this.onTapChoosePointListMenuItem(idx);
				}
			}
		}
	},

	onTapChoosePointListMenuItem: function (idx) {

		switch (idx) {
			case 0 :
				this.getCurrentPosition();
				break;
			case 1:
				this.getMainNavView().push(this.getMapView());
				break;
			default :
				break;
		}
	},

	getCurrentPosition: function () {
		var me = this;
		document.addEventListener("deviceready", onDeviceReady, false);

		navigator.geolocation.getCurrentPosition(
			function success(position) {

				var lat = position.coords.latitude;
				var lng = position.coords.longitude;
				var routesView = me.getRoutesView();

				var point = lat + ',' + lng;
				var fieldStr = point;
				var appKey = 'Fmjtd%7Cluub2501ng%2C2a%3Do5-9uzl00';

				//todo duplicate # 2 move to sep class
				Ext.data.JsonP.request({
					url: 'http://www.mapquestapi.com/geocoding/v1/reverse?key=' + appKey,
					callbackKey: 'callback',
					async: false,
					timeout: 20000,
					params: {
						location: point
					},

					success: function (result, request) {
						var location = result.results[0].locations[0];

						if (location) {
							fieldStr = location.street;
							if (location.adminArea4 && location.adminArea4.length > 0)
								fieldStr = fieldStr + ", " + location.adminArea4;
						}

						me.setLocationFieldValue(fieldStr, point);
						me.getMainNavView().pop(me.getRoutesView());
					},

					failure: function (result, request) {

						me.setLocationFieldValue(fieldStr, point);
						me.getMainNavView().pop(me.getRoutesView());
					}
				});
			},
			function error(error) {
				alert(error);
			});

		function onDeviceReady() {
			console.log("device is ready");
		}
	},

	//todo duplicate # 0 move to sep class
	setLocationFieldValue: function (value, latlng) {

		var routesView = this.getRoutesView();

		if (this.getChoosePointView().isAFieldTapped()) {
			routesView.setAPoint(value);
			routesView.setAPointLatLng(latlng)
		} else {
			routesView.setBPoint(value);
			routesView.setBPointLatLng(latlng)
		}
	}
});