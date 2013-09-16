Ext.define('mobile-client-sencha.controller.HomeController', {
	extend: 'Ext.app.Controller',

	config: {
		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.MapView'
		],

		control: {
			'button[id=routesBtn]': {
				tap: "onTapRoutesBtn"
			},

			'button[id=mapBtn]': {
				tap: "onTapMapBtn"
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

			mapView: {
				autoCreate: true,
				selector: '#mapView',
				xtype: 'MapView'
			}
		}
	},

	onTapRoutesBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		Ext.Viewport.setActiveItem(this.getRoutesView());
	},

	onTapMapBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		Ext.Viewport.setActiveItem(this.getMapView());
	}


});