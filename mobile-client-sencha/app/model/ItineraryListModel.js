Ext.define('mobile-client-sencha.model.ItineraryListModel', {

	extend: 'Ext.data.Model',

	config: {
		fields: [

			{
				name: 'isLeg', type: 'boolean', defaultValue: true
			}
		]
	}

});