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
				xtype: 'toolbar',
				docked: 'top',
				//cls: 'x-toolbar',
				/*ui: 'dark',*/
				/*iconCls: 'home',
				 iconMask: true*/
				items: [
					{
						xtype: 'button',
						iconCls: 'home',
						iconMask: true,
						ui: 'action',
						id: 'mapHomeBtn'
					}
				]
			},

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
				//cls: 'x-toolbar',
				/*ui: 'dark',*/

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
	}



});