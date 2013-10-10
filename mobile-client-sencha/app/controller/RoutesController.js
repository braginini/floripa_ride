Ext.define('mobile-client-sencha.controller.RoutesController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ChoosePointView',
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

			choosePointView: {
				autoCreate: true,
				selector: '[id=choosePointView]',
				xtype: 'ChoosePointView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {

			routesView: {
				show: 'onShow'
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
			},

			pointsMenu: {
				itemtap: function (list, idx, target, record, evt) {
					setTimeout(function() {
						list.deselect(idx);
					}, 100);
					this.onTapPointMenuItem(idx);
				}
			}
		}


	},

	onShow: function() {
		console.log("sdddsdd");
	},

	onTapPointMenuItem: function (idx) {

		this.getPointsMenu().setData(0);

		switch (idx) {
			case 0 :
				this.onTapField("aField");
				break;
			case 1:
				this.onTapField("bField");
				break;
			default :
				break;
		}
	},

	onTapField: function (field) {

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

		this.getMainNavView().getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		this.getMainNavView().push(choosePointView);
	},

	onSearchRouteBtnTap: function () {
		var me = this;
		me.getItinerariesList().getStore().removeAll();  //empty store

		var aPointLatLng = me.getRoutesView().getAFieldLatLngValue();
		var bPointLatLng = me.getRoutesView().getBFieldLatLngValue();

		var date = otp.util.DateUtils.dateToIsoDateString(me.getDateField().getValue());
		var time = otp.util.DateUtils.parseTime(me.getTimeField().getFormattedValue(), "g:ia");
		var arriveBy = me.getDepartField().getValue() == 0 ? false : true;

		if (!aPointLatLng)
			aPointLatLng = me.getAField().getValue();

		if (!bPointLatLng)
			bPointLatLng = me.getBField().getValue();

		if (aPointLatLng.length != 0 && bPointLatLng.length != 0) {
			Ext.data.JsonP.request({
				url: 'http://ec2-54-232-241-207.sa-east-1.compute.amazonaws.com:8080/opentripplanner-api-webapp/ws/plan',
				callbackKey: 'callback',
				async: false,
				timeout: 20000,
				params: {
					_dc: Date.now(),
					fromPlace: aPointLatLng, //'-27.593692,-48.543871',
					toPlace: bPointLatLng, //'-27.589889,-48.516748',
					ui_date: '10/3/2013',
					arriveBy: arriveBy,
					time: time,
					mode: 'TRANSIT,WALK',
					optimize: 'QUICK',
					maxWalkDistance: '840',
					date: date,
					walkSpeed: '1.341'
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
		}
	}
});