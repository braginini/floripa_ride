Ext.define('mobile-client-sencha.controller.MapController', {
	extend: 'Ext.app.Controller',

	config: {

		markerLayer: null,

		views: [
			'mobile-client-sencha.view.HomeView',
			'mobile-client-sencha.view.MapView'
		],

		refs: {
			mapCmp: '#leafletmap',
			homeBtn: '#mapHomeBtn',

			homeView: {
				autoCreate: true,
				selector: '#homeView',
				xtype: 'HomeView'
			},

			mapView: {
				autoCreate: true,
				selector: '#mapView',
				xtype: 'MapView'
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
			}
		}
	},

	//todo when the view is opened clear map. Find the event

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

		this.markerLayer.clearLayers();
		var latLng = e.latlng;
		var marker = L.marker([latLng.lat, latLng.lng]);
		this.markerLayer.addLayer(marker);
	},

	onTapHomeBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'right'
		});

		this.markerLayer.clearLayers(); //todo remove from here
		Ext.Viewport.setActiveItem(this.getHomeView());
	}
});