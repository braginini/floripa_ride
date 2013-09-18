Ext.define('mobile-client-sencha.controller.ChoosePointController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.MapView'
		],

		control: {
			'button[id=homeBtn1]': {
				tap: 'onTapHomeBtn'
			},

			'list[id=choosePointMenu]': {
				itemtap: function (list, idx, target, record, evt) {
					this.onTapChoosePointListMenuItem(idx);
				}
			}
		},

		refs: {
			homeView: {
				autoCreate: true,
				selector: '#homeView',
				xtype: 'HomeView'
			},

			routesView: {
				autoCreate: true,
				selector: '#routesView',
				xtype: 'RoutesView'
			},

			choosePointView: {
				autoCreate: true,
				selector: '#choosePointView',
				xtype: 'ChoosePointView'
			},

			mapView: {
				autoCreate: true,
				selector: '#mapView',
				xtype: 'MapView'
			}
		}
	},

	onTapHomeBtn: function (button, e, eOpts) {
		this.changeView(this.getHomeView(), 'slide', 'right');
	},

	changeView: function (view, type, slideDirection) {

		Ext.Viewport.getLayout().setAnimation({
			type: type,
			direction: slideDirection
		});

		Ext.Viewport.setActiveItem(view);

	},

	onTapChoosePointListMenuItem: function (idx) {

		switch (idx) {
			case 0 :
				this.getCurrentPosition();
				break;
			case 1:
				this.changeView(this.getMapView(), 'slide', 'left');
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

				var fieldStr = lat + ',' + lng;
				if (me.getChoosePointView().isAFieldTapped()) {
					routesView.setAFieldValue(fieldStr);
				} else {
					routesView.setBFieldValue(fieldStr);
				}

				me.changeView(routesView, 'slide', 'right')
			},
			function error (error) {
				alert(error);
			});

		function onDeviceReady() {
			console.log("device is ready");
		}
	}
});