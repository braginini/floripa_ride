Ext.define('mobile-client-sencha.controller.RoutesController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView'
		],

		refs: {

			searchRouteBtn: '#searchRouteBtn',
			aField: '#aField',
			bField: '#bField',
			itinerariesList: '#itineraries',

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
			}
		},

		control: {

			'button[id=homeBtn]': {
				tap: 'onTapHomeBtn'
			},

			'textfield[id=aField]': {
				tap: function () {
					this.onTapField('aField')
				}
			},

			'textfield[id=bField]': {
				tap: function () {
					this.onTapField('bField')
				}
			},

			searchRouteBtn: {
				tap: 'onSearchRouteBtnTap'
			}
		}

	},

	onTapHomeBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'right'
		});

		Ext.Viewport.setActiveItem(this.getHomeView());
	},

	onTapField: function (field) {

		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		var choosePointView = this.getChoosePointView();
		var text = '';
		var isAFieldTapped = false;
		switch (field) {
			case 'aField' :
				text = 'Encontrar o ponto de partida';
				isAFieldTapped = true;
				break;
			case 'bField' :
				text = 'Encontrar o ponto final';
				isAFieldTapped = false;
				break;
			default :
				text = 'Encontrar um ponto'
				isAFieldTapped = false;
				break;
		}

		choosePointView.setSearchFieldPlaceHolder(text);
		choosePointView.setFieldTapped(isAFieldTapped);
		Ext.Viewport.setActiveItem(choosePointView);
	},

	onSearchRouteBtnTap: function () {

		var aPointLatLng = this.getRoutesView().getAFieldLatLngValue();
		var bPointLatLng = this.getRoutesView().getBFieldLatLngValue();
		var me = this;

		if (!aPointLatLng)
			aPointLatLng = this.getAField().getValue();

		if (!bPointLatLng)
			bPointLatLng = this.getBField().getValue();

		//if (aPointLatLng.length != 0 && bPointLatLng.length != 0) {

			Ext.data.JsonP.request({
				url: 'http://ec2-54-232-241-207.sa-east-1.compute.amazonaws.com:8080/opentripplanner-api-webapp/ws/plan',
				callbackKey: 'callback',
				async: false,
				timeout: 20000,
				params: {
					_dc: 1380747025820,
					fromPlace: '-27.593084,-48.531511',//aPointLatLng,
					toPlace: '-27.600082,-48.465422',//bPointLatLng,
					ui_date: '10/2/2013',
					arriveBy: 'false',
					time: '4:45pm',
					mode: 'TRANSIT,WALK',
					optimize: 'QUICK',
					maxWalkDistance: '840',
					date: '2013-10-02',
					walkSpeed: '1.341',
					numItineraries: '6'
				},

				success: function (result, request) {
					//todo handle errors sent by server
					var itineraries = new Array();

					if (result.plan && result.plan.itineraries.length > 0) {

						for (var i = 0; i < result.plan.itineraries.length; i++) {
							me.getItinerariesList().getStore().add(result.plan.itineraries[i]);
						}

					}
				},

				failure: function (result, request) {
					alert("failed");
				}
			});
		//}
	}
});