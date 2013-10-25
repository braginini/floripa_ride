Ext.define('mobile-client-sencha.controller.MapController', {
	extend: 'Ext.app.Controller',

	config: {

		markerLayer: null,

		clickCount: 0,

		toCancel: true,

		views: [
			'mobile-client-sencha.view.MapView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.MainNavView'
		],

		refs: {
			mapCmp: '[id=leafletmap]',
			addMapBtn: '[id=addMapBtn]',

			mapView: {
				autoCreate: true,
				selector: '[id=mapView]',
				xtype: 'MapView'
			},

			choosePointView: {
				autoCreate: true,
				selector: '[id=choosePointView]',
				xtype: 'ChoosePointView'
			},

			routesView: {
				autoCreate: true,
				selector: '[id=routesView]',
				xtype: 'RoutesView'
			},

			mainNavView: {
				autoCreate: true,
				selector: '[id=mainNavView]',
				xtype: 'MainNavView'
			}
		},

		control: {
			mapCmp: {
				maprender: 'onMapRender',
				zoomend: 'onZoomEnd',
				movestart: 'onMoveStart',
				moveend: 'onMoveEnd',
				click: 'onMapClick'
			},

			mapView: {
				//show: 'onShow',
				mapOpen: 'onMapOpen'
			},

			addMapBtn: {
				tap: 'onTapAddMapBtn'
			}
		}
	},

	onMapOpen: function() {
		//console.log("2");
	},

	onShow: function () {
		console.log("map shown");
		this.changeAddMapButtonMode('Cancelar', true);
		if (this.markerLayer) {
			this.markerLayer.clearLayers();
			console.log("cleared map");
		}
	},

	onMapRender: function (component, map, layer) {
		//add the layer for the markers
		this.markerLayer = L.layerGroup().addTo(map);

	},

	onZoomEnd: function (component, map, layer, zoom) {
	},

	onMoveStart: function (component, map, layer) {
	},

	onMoveEnd: function (component, map, layer) {
	},

	onMapClick: function (component, map, layer, e) {
		console.log("click " + e.latlng);
		var me = this;
		me.setClickCount(me.getClickCount() + 1);
		if (me.getClickCount() <= 1) {

			window.setTimeout(function () {
				if (me.getClickCount() <= 1) {
					var latLng = e.latlng;
					me.addSingleMarker(latLng.lat, latLng.lng);
					me.setToCancel(false);
					me.changeAddMapButtonMode('Pronto', false);
				}
				me.setClickCount(0);
			}, 200);
		}
	},

	//removes all other markers
	addSingleMarker: function (lat, lng) {
		this.markerLayer.clearLayers();
		var marker = L.marker([lat, lng]);
		this.markerLayer.addLayer(marker);
	},

	onTapAddMapBtn: function (button, e, eOpts) {

		var me = this;

		if (this.getToCancel()) {
			//me.getMainNavView().pop(me.getChoosePointView());
			me.getMainNavView().pop();
		} else {
			var routesView = this.getRoutesView();

			var point = this.markerLayer.getLayers()[0].getLatLng().lat + ',' + this.markerLayer.getLayers()[0].getLatLng().lng;
			var fieldStr = point; //by default field value - lat + lng
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
					me.getMainNavView().pop(routesView);
				},

				failure: function (result, request) {

					me.setLocationFieldValue(fieldStr, point);
					me.getMainNavView().pop(routesView);
				}
			});
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

		routesView.fireEvent('pointFieldChange');
	},

	changeAddMapButtonMode: function (text, mode) {

		this.getAddMapBtn().setText(text);
		this.setToCancel(mode);
	}
});