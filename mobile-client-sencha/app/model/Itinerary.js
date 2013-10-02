Ext.define('mobile-client-sencha.model.Itinerary', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name: 'duration'
			} ,
			{
				name: 'startTime',
				convert: function (millis) {
					var date = new Date(+millis);
					return date.getHours() + ":" + date.getMinutes();
				}

			} ,
			{
				name: 'endTime',
				convert: function (millis) {
					var date = new Date(+millis);
					return date.getHours() + ":" + date.getMinutes();
				}
			} ,
			{
				name: 'fare'
			} ,
			{
				name: 'transfers'
			},
			{
				name: 'transitTime'
			},
			{
				name: 'waitingTime'
			},
			{
				name: 'walkDistance'
			},
			{
				name: 'walkTime'
			},
			{
				name: 'legs', type: 'Leg[]'
			}
		]
	}

})