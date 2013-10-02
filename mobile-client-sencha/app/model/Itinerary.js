Ext.define('mobile-client-sencha.model.Itinerary', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name : 'duration'
			} ,
			{
				name : 'startTime'
			} ,
			{
				name : 'endTime'
			} ,
			{
				name : 'fare'
			} ,
			{
				name : 'transfers'
			},
			{
				name : 'transitTime'
			},
			{
				name : 'waitingTime'
			},
			{
				name : 'walkDistance'
			},
			{
				name : 'walkTime'
			},
			{
				name : 'legs', type: 'Leg[]'
			}
		]
	}

})