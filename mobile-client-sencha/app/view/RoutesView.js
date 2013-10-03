Ext.define('mobile-client-sencha.view.RoutesView', {
	extend: 'Ext.Panel',

	id: 'routesView',

	xtype: 'RoutesView',

	requires: [
		'Ext.SegmentedButton',
		'Ext.ux.field.TimePicker'
	],

	config: {

		aFieldLatLng: null,

		bFieldLatLng: null,

		fullscreen: true,

		layout: 'vbox',

		items: [
			{
				xtype: 'toolbar',
				docked: 'top',
				title: 'Como obter?',
				items: [
					{
						xtype: 'button',
						iconCls: 'home',
						iconMask: true,
						ui: 'action',
						id: 'homeBtn'
					}
				]
			},

			{
				xtype: 'textfield',
				placeHolder: 'A: Especificar o ponto de partida',
				name: 'firstName',
				margin: 15,
				id: 'aField',
				disabled: "true",

				listeners: {
					element: 'element',
					tap: function () {
						this.fireEvent('tap');
					}
				}
			},
			{
				xtype: 'textfield',
				placeHolder: 'B: Especificar o ponto final',
				name: 'lastName',
				margin: 15,
				id: 'bField',
				disabled: "true",

				listeners: {
					element: 'element',
					tap: function () {
						this.fireEvent('tap');
					}
				}
			},
			{
				xtype: 'segmentedbutton',
				allowDepress: true,
				margin: 15,
				items: [

					{
						pressed: true,
						iconCls: 'team',
						iconMask: true
					},
					{
						iconCls: 'team',
						iconMask: true
					},
					{
						iconCls: 'team',
						iconMask: true
					}
				]
			},

			{
				xtype: 'container',
				layout: 'vbox',
				items: [
					{
						xtype: 'selectfield',
						margin : 15,
						options: [
							{text: 'Depart',  value: 0},
							{text: 'Arrive', value: 1}
						]
					},

					{
						xtype: 'timepickerfield',
						value: new Date(),
						margin : 15
					},

					{
						xtype: 'datepickerfield',
						value: new Date(),
						margin : 15
					}
				]
			},

			{
				xtype: 'button',
				text: 'Ok',
				margin: 15,
				id: 'searchRouteBtn'
			},
			{
				xtype: 'list',
				id: 'itineraries',
				itemTpl: '{startTime} - {endTime}',
				margin: 15,
				store: 'Itinerary',
				flex: 1
			}

		]
	},

	setAFieldValue: function (value) {
		this.setFieldValue(this.down('#aField'), value);
	},

	setBFieldValue: function (value) {
		this.setFieldValue(this.down('#bField'), value);
	},

	setFieldValue: function (field, value) {
		field.setValue(value);
	},

	setFieldValueById: function (id, value) {
		this.down(id).setValue(value);
	},

	setAFieldLatLngValue: function (value) {
		this.setAFieldLatLng(value);
	},

	setBFieldLatLngValue: function (value) {
		this.setBFieldLatLng(value);
	},

	getAFieldLatLngValue: function () {
		return this.getAFieldLatLng();
	},

	getBFieldLatLngValue: function () {
		return this.getBFieldLatLng();
	}

});