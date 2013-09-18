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
			}, 500);
		}
	},

	//removes all other markers
	addSingleMarker: function (lat, lng) {
		this.markerLayer.clearLayers();
		var marker = L.marker([lat, lng]);
		this.markerLayer.addLayer(marker);
	},

	onTapAddMapBtn: function (button, e, eOpts) {
		if (this.getToCancel()) {
			this.changeView(this.getChoosePointView(), 'slide', 'right');
		} else {
			var routesView = this.getRoutesView();

			var fieldStr = this.markerLayer.getLayers()[0].getLatLng().lat + ',' + this.markerLayer.getLayers()[0].getLatLng().lng;
			if (this.getChoosePointView().isAFieldTapped()) {
				routesView.setAFieldValue(fieldStr);
			} else {
				routesView.setBFieldValue(fieldStr);
			}

			this.changeView(routesView, 'slide', 'right')
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