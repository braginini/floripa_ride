Ext.define('mobile-client-sencha.view.RouteParametersView', {
	extend: 'Ext.Panel',

	id: 'routeParametersView',

	xtype: 'RouteParametersView',

	requires: [
		'Ext.ux.field.TimePicker',
		'Ext.form.FieldSet',
		'Ext.field.Select'
	],

	config: {

		fullscreen: true,

		layout: 'vbox',

		items: [
			{
				xtype: 'fieldset',
				items: [
					{
						xtype: 'container',
						layout: 'vbox',
						items: [
							{
								xtype: 'selectfield',
								id: 'departField',
								label: 'Depart:',
								options: [
									{text: 'Depart', value: 0},
									{text: 'Arrive', value: 1}
								]
							},

							{
								xtype: 'timepickerfield',
								value: new Date(),
								label: 'Time:',
								id: 'timeField'
							},

							{
								xtype: 'datepickerfield',
								label: 'Date:',
								value: new Date(),
								id: 'dateField'
							}
						]
					},

					{
						xtype: 'container',

						layout: {
							pack: 'center',
							type: 'hbox'
						},

						items: [
							{
								xtype: 'button',
								text: 'Cancel',
								margin: 15,
								id: 'cancelParamsBtn'
							},

							{
								xtype: 'button',
								text: 'Ok',
								margin: 15,
								id: 'okParamsBtn'
							}
						]
					}
				]
			}

		]
	},

	getTimeFieldValue: function () {

		var timeField = this.down('#timeField');

		if (timeField)
			return timeField.getFormattedValue();

	},

	getDateFieldValue: function () {

		var timeField = this.down('#dateField');

		if (timeField)
			return timeField.getValue();

	},

	getDepartFieldValue: function () {

		var timeField = this.down('#departField');

		if (timeField)
			return timeField.getValue();

	}

});