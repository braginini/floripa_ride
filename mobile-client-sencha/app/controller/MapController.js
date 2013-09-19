Ext.define('mobile-client-sencha.controller.MapController', {
	extend: 'Ext.app.Controller',

	config: {

		markerLayer: null,

		clickCount: 0,

		toCancel: true,

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.MapView',
			'mobile-client-sencha.view.ChoosePointView',
			'mobile-client-sencha.view.RoutesView'
		],

		refs: {
			mapCmp: '#leafletmap',
			homeBtn: '#mapHomeBtn',
			addMapBtn: '#addMapBtn',

			homeView: {
				autoCreate: true,
				selector: '#homeView',
				xtype: 'HomeView'
			},

			mapView: {
				autoCreate: true,
				selector: '#mapView',
				xtype: 'MapView'
			},

			choosePointView: {
				autoCreate: true,
				selector: '#choosePointView',
				xtype: 'ChoosePointView'
			},

			routesView: {
				autoCreate: true,
				selector: '#routesView',
				xtype: 'RoutesView'
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

			homeBtn: {
				tap: 'onTapHomeBtn'
			},

			addMapBtn: {
				tap: 'onTapAddMapBtn'
			}
		}
	},

	//todo when the view is opened clear map. Find the event
	//todo when view is showed - setToCancel = true

	onMapRender: function (component, map, layer) {
		console.log("map render");
		//add the layer for the markers
		this.markerLayer = L.layerGroup().addTo(map);

	},
	onZoomEnd: function (component, map, layer, zoom) {
		console.log("zoom end -> new zoom level: " + zoom);
	},
	onMoveStart: function (component, map, layer) {
		console.log("move start");
	},
	onMoveEnd: function (component, map, layer) {
		console.log("move end");
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
					me.changeAddMapButtonMode();
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
			this.changeView(this.getChoosePointView(), 'slide', 'right');
		} else {
			var routesView = this.getRoutesView();

			var point = this.markerLayer.getLayers()[0].getLatLng().lat + ',' + this.markerLayer.getLayers()[0].getLatLng().lng;
			var fieldStr = point; //by default field value - lat + lng
			var appKey = 'Fmjtd%7Cluub2501ng%2C2a%3Do5-9uzl00';
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
							fieldStr = fieldStr + ", "  + location.adminArea4;
					}

					me.setLocationFieldValue(fieldStr, point);
					me.changeView(routesView, 'slide', 'right')
				},

				failure: function (result, request) {

					me.setLocationFieldValue(fieldStr, point);
					me.changeView(routesView, 'slide', 'right')
				}
			});
		}
	},

	setLocationFieldValue: function (value, latlng) {

		var routesView = this.getRoutesView();

		if (this.getChoosePointView().isAFieldTapped()) {
			routesView.setAFieldValue(value);
			routesView.setAFieldLatLngValue(latlng)
		} else {
			routesView.setBFieldValue(value);
			routesView.setBFieldLatLngValue(latlng)
		}
	},

	onTapHomeBtn: function (button, e, eOpts) {
		this.changeView(this.getHomeView(), 'slide', 'right');
		this.markerLayer.clearLayers();
	},

	changeView: function (view, type, slideDirection) {

		Ext.Viewport.getLayout().setAnimation({
			type: type,
			direction: slideDirection
		});

		Ext.Viewport.setActiveItem(view);
	},

	changeAddMapButtonMode: function () {

		this.getAddMapBtn().setText('Pronto');
		this.setToCancel(false);
	}
});