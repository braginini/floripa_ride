Ext.define('mobile-client-sencha.model.Itinerary', {

	extend: 'Ext.data.Model',

	config: {

		fields: [

			{
				name: 'duration',
				convert: function (millis) {
					var minutes =   Math.floor(millis / 60000);
					var hours = Math.floor(minutes / 60)
					minutes = minutes - hours;
					if (hours > 0) {
						return Math.floor(hours) + ' ' + ((hours > 1) ? 'hours' : 'hour') + ' '
							+ minutes + (minutes > 1 ? ' minutes' : 'minute');
					}

					return minutes + " minutes"
				}
			} ,
			{
				name: 'startTime',
				convert: function (millis) {

					var date = new Date(+millis);
					var hours = (date.getHours() <10 ? '0' : '') + date.getHours() ;
					var minutes = (date.getMinutes()< 10 ? '0' : '') + date.getMinutes() ;

					console.log(new Date(+millis - date.getTimezoneOffset() * 60 * 1000));
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