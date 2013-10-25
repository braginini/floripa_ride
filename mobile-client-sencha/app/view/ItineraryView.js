Ext.define('mobile-client-sencha.view.RoutesView', {
	extend: 'Ext.Panel',

	id: 'routesView',

	xtype: 'RoutesView',

	requires: [
		'Ext.SegmentedButton',
		'Ext.ux.field.TimePicker'
	],

	config: {

		layout: 'vbox',

		items: [

			{
				xtype: 'list',
				id: 'legsList',
				store: 'Leg',
				itemTpl: ''

			}

		]
	}

});