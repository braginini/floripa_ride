Ext.define('mobile-client-sencha.controller.MainNavController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.MainNavView',
			'mobile-client-sencha.view.RouteParametersView',
			'mobile-client-sencha.view.MapView'
		],

		refs: {

			changePointsBtn: '[id=changePointsBtn]',
			choosePointSearch: '[id=choosePointSearch]',
			refreshParametersBtn: '[id=refreshParametersBtn]',

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

			routeParametersView: {
				autoCreate: true,
				selector: '[id=routeParametersView]',
				xtype: 'RouteParametersView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {

			mainNavView: {
				activeitemchange: 'onActiveItemChange'
			}
		}


	},

	onActiveItemChange: function(navView, value, oldValue, eOpts) {

		var newViewId = value.getId();
		var oldViewId = oldValue.getId();

		if (newViewId && oldViewId) {

			if (newViewId == this.getChoosePointView().getId() ) {

				this.hideDisplayChoosePointSearchField(false);
				this.hideChangePointsBtn(true);
				this.hideRefreshParametersBtn(true);

			} else if (newViewId == this.getRoutesView().getId()) {

				this.hideRefreshParametersBtn(true);
				this.hideDisplayChoosePointSearchField(true);
				this.hideChangePointsBtn(false);

			}  else if (newViewId == this.getMapView().getId()) {

				this.hideRefreshParametersBtn(true);
				this.hideDisplayChoosePointSearchField(true);
				this.hideChangePointsBtn(true);
				this.hideRefreshParametersBtn(true);
			} else if (newViewId == this.getRouteParametersView().getId()) {
				this.hideRefreshParametersBtn(false);
				this.hideDisplayChoosePointSearchField(true);
				this.hideChangePointsBtn(true);
			}

			//todo else if(other views)
		}
	},

	hideChangePointsBtn: function(toHide) {
		var cmp = this.getChangePointsBtn();

		if (cmp)
			cmp.setHidden(toHide);
	},

	hideRefreshParametersBtn: function(toHide) {
		var cmp = this.getRefreshParametersBtn();

		if (cmp)
			cmp.setHidden(toHide);
	},

	hideDisplayChoosePointSearchField: function(toHide) {
		var search = this.getChoosePointSearch();

		if (search)
			search.setHidden(toHide);
	}
});