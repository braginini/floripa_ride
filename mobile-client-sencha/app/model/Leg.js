Ext.define('mobile-client-sencha.model.Leg', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name : 'agencyId'
			} ,
			{
				name : 'agencyName'
			} ,
			{
				name : 'agencyUrl'
			} ,
			{
				name : 'distance'
			} ,
			{
				name : 'duration'
			},
			{
				name : 'endTime'
			},
			{
				name : 'headSign'
			},
			{
				name : 'mode'
			},
			{
				name : 'startTime'
			},
			{
				name : 'route'
			},
			{
				name : 'routeId'
			},
			{
				name : 'routeLongName'
			},
			{
				name : 'routeShortName'
			},
			{
				name : 'tripId'
			},
			{
				name : 'from', type: 'Destination'
			},
			{
				name : 'to', type: 'Destination'
			}
		]
	}

})
