Ext.define('mobile-client-sencha.view.MainNavView', {
	extend: 'Ext.NavigationView',

	id: 'routesView',

	xtype: 'RoutesView',

	requires: [
		'mobile-client-sencha.view.RoutesView'
	],

	config: {

		navigationBar : {
			docked : 'top',
			layout: 'fit',
			items : [
				{
					hidden: true,
					xtype: 'searchfield',
					flex: 1,
					placeHolder: 'Encontrar um ponto',
					id: 'choosePointSearch'
				}
			]
		},

		aFieldLatLng: null,

		bFieldLatLng: null,

		fullscreen: true,

		layout: 'card',
		items: [

			{
				title: 'Como obter?',
				xtype: 'panel',
				id: 'routesViewPanel',
				layout: 'vbox',
				items: [

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
								id: 'departField',
								options: [
									{text: 'Depart',  value: 0},
									{text: 'Arrive', value: 1}
								]
							},

							{
								xtype: 'timepickerfield',
								value: new Date(),
								id: 'timeField',
								margin : 15
							},

							{
								xtype: 'datepickerfield',
								value: new Date(),
								id: 'dateField',
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
						itemTpl: '{startTime} - {endTime} {transfers} transfers {duration}',
						margin: 15,
						store: 'Itinerary',
						flex: 1
					}

				]
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