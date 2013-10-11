Ext.define('mobile-client-sencha.controller.RouteParametersController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.RouteParametersView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.MainNavView'
		],

		refs: {

			searchRouteBtn: '[id=searchRouteBtn]',
			aField: '[id=aField]',
			bField: '[id=bField]',
			itinerariesList: '[id=itineraries]',
			timeField: '[id=timeField]',
			dateField: '[id=dateField]',
			departField: '[id=departField]',
			choosePointSearch: '[id=choosePointSearch]',
			pointsMenu: 'list[id=pointsMenu]',
			okParamsBtn: 'button[id=okParamsBtn]',

			routesView: {
				autoCreate: false,
				selector: '[id=routesView]',
				xtype: 'RoutesView'

			},

			routeParameterView: {
				autoCreate: true,
				selector: '[id=routeParameterView]',
				xtype: 'RouteParametersView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {

			okParamsBtn: {
				tap: 'onOkBtnTap'
			}

		}

	},

	onOkBtnTap: function() {
		console.log("Ok tap");
		this.getTimeField().getValue();
	}

});