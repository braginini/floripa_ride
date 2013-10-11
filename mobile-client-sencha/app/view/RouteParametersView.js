Ext.define('mobile-client-sencha.view.RouteParametersView', {
	extend: 'Ext.Panel',

	id: 'routeParametersView',

	xtype: 'RouteParametersView',

	requires: [
		'Ext.ux.field.TimePicker'
	],

	config: {

		fullscreen: true,

		layout: 'vbox',

		items: [
			{
				xtype: 'fieldset',
				items:[
					{
						xtype: 'container',
						layout: 'vbox',
						items: [
							{
								xtype: 'selectfield',
								id: 'departField',
								options: [
									{text: 'Depart', value: 0},
									{text: 'Arrive', value: 1}
								]
							},

							{
								xtype: 'timepickerfield',
								value: new Date(),
								id: 'timeField'
							},

							{
								xtype: 'datepickerfield',
								value: new Date(),
								id: 'dateField'
							}
						]
					},

					{
						xtype: 'button',
						text: 'Ok',
						margin: 15,
						id: 'btn'
					}
				]
			}

		]
	}

});