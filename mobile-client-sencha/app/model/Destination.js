Ext.define('mobile-client-sencha.model.Destination', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name : 'arrival'
			} ,
			{
				name : 'departure'
			} ,
			{
				name : 'geometry'
			} ,
			{
				name : 'lat'
			} ,
			{
				name : 'lon'
			},
			{
				name : 'name'
			},
			{
				name : 'stopId'
			},
			{
				name : 'tripId'
			}
		]
	}

})
