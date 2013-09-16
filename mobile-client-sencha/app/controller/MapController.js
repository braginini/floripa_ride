Ext.define('mobile-client-sencha.controller.MapController', {
	extend: 'Ext.app.Controller',

	config: {

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
				moveend: 'onMoveEnd'
			},

			homeBtn: {
				tap: 'onTapHomeBtn'
			}
		}
	},

	onMapRender: function(component, map, layer) {
		console.log("map render");
	},
	onZoomEnd: function(component, map, layer, zoom) {
		console.log("zoom end -> new zoom level: " + zoom);
	},
	onMoveStart: function(component, map, layer) {
		console.log("move start");
	},
	onMoveEnd: function(component, map, layer) {
		console.log("move end");
	},

	onTapHomeBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'right'
		});

		Ext.Viewport.setActiveItem(this.getHomeView());
	}
});