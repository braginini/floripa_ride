Ext.define('mobile-client-sencha.controller.MainNavController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.MainNavView',
			'mobile-client-sencha.view.MapView'
		],

		refs: {

			//routeModeBtn: 'segmentedbutton[id=routeModeBtn]',
			choosePointSearch: 'searchField[id=choosePointSearch]',

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
				this.hideDisplayRouteModeBtn(true);

			} else if (newViewId == this.getRoutesView().getId()) {

				this.hideDisplayChoosePointSearchField(true);
				this.hideDisplayRouteModeBtn(false);

			}  else if (newViewId == this.getMapView().getId()) {

				console.log("0");
				this.hideDisplayChoosePointSearchField(true);
				this.hideDisplayRouteModeBtn(true);
				//this.getMapView().fireEvent('mapOpen');
				this.getMapView().mapOpen();
			}

			//todo else if(other views)
		}
	},

	hideDisplayRouteModeBtn: function(toHide) {
		/*var cmp = this.getRouteModeBtn();

		if (cmp)
			cmp.setHidden(toHide);*/
	},

	hideDisplayChoosePointSearchField: function(toHide) {
		var search = this.getChoosePointSearch();

		if (search)
			search.setHidden(toHide);
	}
});