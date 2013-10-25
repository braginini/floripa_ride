Ext.define('mobile-client-sencha.view.MapView', {
	extend: 'Ext.Panel',

	id: 'mapView',

	xtype: 'MapView',

	requires: [
		'Ext.SegmentedButton',
		'Ext.ux.LeafletMap'
	],

	config: {
		layout: 'fit',
		items: [

			{
				xtype: 'leafletmap',
				id: 'leafletmap',
				useCurrentLocation: true,
				autoMapCenter: false,
				enableOwnPositionMarker: false,
				mapOptions: {
					zoom: 15
				}
			},

			{
				xtype: 'toolbar',
				docked: 'bottom',
				ui: 'dark',

				items: [
					{
						xtype: 'button',
						ui: 'action',
						id: 'addMapBtn',
						text:'Cancelar'
					}
				]
			}
		]
	},

	mapOpen: function() {
		//console.log('1');
		this.fireEvent('mapOpen');
	}
});