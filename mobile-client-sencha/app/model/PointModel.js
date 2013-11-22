Ext.define('mobile-client-sencha.model.PointModel', {

	extend: 'mobile-client-sencha.model.ItineraryListModel',

	config: {
		fields: [

			{
				name: 'arrival'
			},
			{
				name: 'departure'
			},
			{
				name: 'geometry'
			},
			{
				name: 'lat'
			},
			{
				name: 'lon'
			},
			{
				name: 'name'
			},
			{
				name: 'orig'
			},
			{
				name: 'stopCode'
			},
			{
				name: 'stopId'
			},
			{
				name: 'zoneId'
			}
		]
	}

});