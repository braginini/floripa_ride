Ext.define('mobile-client-sencha.model.Itinerary', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name: 'duration',
				convert: function (millis) {
					var minutes =  millis / 1000 / 60;
					var hours = minutes / 60;
					return millis;
				}
			} ,
			{
				name: 'startTime',
				convert: function (millis) {
					var date = new Date(+millis);
					var hours = (date.getHours() <10 ? '0' : '') + date.getHours() ;
					var minutes = (date.getMinutes()< 10 ? '0' : '') + date.getMinutes() ;
					return  hours + ":" + minutes;
				}

			} ,
			{
				name: 'endTime',
				convert: function (millis) {
					var date = new Date(+millis);
					var hours = (date.getHours().length < 2) ? ("0" + date.getHours()) : date.getHours();
					var minutes = (date.getMinutes().length < 2) ? ("0" + date.getMinutes()) : date.getMinutes();
					return  hours + ":" + minutes;
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