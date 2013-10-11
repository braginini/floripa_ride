Ext.define('mobile-client-sencha.controller.RoutesParametersController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.RoutesParametersView',
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

			routesView: {
				autoCreate: false,
				selector: '[id=routesView]',
				xtype: 'RoutesView'

			},

			routesParameterView: {
				autoCreate: true,
				selector: '[id=routesParameterView]',
				xtype: 'RoutesParametersView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {

		}

	}

});