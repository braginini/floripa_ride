Ext.define('mobile-client-sencha.controller.RouteParametersController', {
	extend: 'Ext.app.Controller',

	config: {

		dateFieldValue: null,

		departFieldValue: null,

		timeFieldValue: null,

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
			cancelParamsBtn: 'button[id=cancelParamsBtn]',
			refreshParametersBtn: '[id=refreshParametersBtn]',

			routesView: {
				autoCreate: false,
				selector: '[id=routesView]',
				xtype: 'RoutesView'

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

			okParamsBtn: {
				tap: 'onOkBtnTap'
			},

			cancelParamsBtn: {
				tap: 'onActivate'
			},

			routeParametersView: {
				activate: 'onActivate'
			},

			refreshParametersBtn: {
				tap: 'onTapRefreshBtn'
			}

		}

	},

	onTapRefreshBtn: function() {
		this.getTimeField().setValue(new Date());
		this.getDateField().setValue(new Date());
	},

	onActivate: function() {

		if (this.dateFieldValue) {
			this.getDateField().setValue(this.dateFieldValue);
		} else {
			this.dateFieldValue = new Date(this.getDateField().getValue());
		}

		if (this.timeFieldValue) {
			this.getTimeField().setValue(this.timeFieldValue);
		} else {
			this.timeFieldValue = new Date(this.getTimeField().getValue());
		}

		if (this.departFieldValue != null) {
			this.getDepartField().setValue(this.departFieldValue);
		} else {
			this.departFieldValue = this.getDepartField().getValue();
		}

	},

	onOkBtnTap: function() {
		this.dateFieldValue = new Date(this.getDateField().getValue());
		this.timeFieldValue = new Date(this.getTimeField().getValue());
		this.departFieldValue = this.getDepartField().getValue();

		var date = otp.util.DateUtils.dateToIsoDateString(this.getDateField().getValue());
		var value = ((this.departFieldValue) ? "Arrive by" : "Depart") + " "
			+ this.getTimeField().getFormattedValue() + ", " + date;

		this.getRoutesView().setRouteParamsFieldValue(value);

		this.getMainNavView().pop(this.getMainNavView().getItems().length - 1);
	}

});