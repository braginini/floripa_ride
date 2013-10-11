Ext.define('mobile-client-sencha.view.RoutesView', {
	extend: 'Ext.Panel',

	id: 'routesView',

	xtype: 'RoutesView',

	requires: [
		'Ext.SegmentedButton',
		'Ext.ux.field.TimePicker'
	],

	config: {

		aFieldLatLng: null,

		bFieldLatLng: null,

		fullscreen: true,

		layout: 'vbox',

		items: [
			{
				xtype: 'list',
				itemTpl: '{title}<span style="float:right; margin-right:10px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAANCAYAAACQN/8FAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAALRJREFUeNpi/P//P8OkSZMkGBgYtgNxfV5e3iYGLIBZSEhIGkjvB2Id IA4+efLkJXNz85voCpmgNBsSvRpogx+GQqBVT4G0LRDfx6cYbCIexd7oViMrvoOkeB1MMSPI18gA KGEPpA4gCYFs0WBCU6QCpJYiCYFscQPa9osJSZE61CRpJEW2QEV34G6EKtqLpOgRVNF95ADHpsgB WRHMxI9A/AWfIliAvwBJAvFudOuQAUCAAQDiG0runJxCpwAAAABJRU5ErkJggg=="/></span>',
				height: 95,
				id: 'pointsMenu',
				data: [
					{
						title: 'A: Especificar o ponto de partida'
					},
					{
						title: 'B: Especificar o ponto final'
					}
				]
			},
			{
				xtype: 'textfield',
				placeHolder: 'Departure 17:46, Mon, 14 Oct.',
				readOnly: true,
				margin: 10,
				id: 'routeParamsField',

				listeners: {
					element: 'element',
					tap: function () {
						this.fireEvent('tap');
					}
				}
			},
			{
				xtype: 'segmentedbutton',
				id: 'routeModeBtn',
				margin: 10,

				layout: {
					type: 'hbox',
					pack: 'center',
					align: 'stretchmax'
				},

				items: [

					{
						pressed: true,
						iconCls: 'team',
						width: '33.33333333333333%',
						iconMask: true
					},
					{
						iconCls: 'team',
						width: '33.33333333333333%',
						iconMask: true
					},
					{
						iconCls: 'team',
						width: '33.33333333333333%',
						iconMask: true
					}
				]

			},

			{
				xtype: 'list',
				id: 'itineraries',
				itemTpl: '{startTime} - {endTime} {transfers} transfers {duration}',
				store: 'Itinerary',
				flex: 1
			}

		]
	},

	setAPoint: function (value) {
		this.setPoint(0, value);
	},

	setBPoint: function (value) {
		this.setPoint(1, value);
	},

	setPoint: function (idx, value) {
		if (value) {
			var record = this.down('#pointsMenu').getStore().getAt(idx);
			record.set('title', value);
		}
	},

	setAPointLatLng: function (value) {
		this.setAFieldLatLng(value);
	},

	setBPointLatLng: function (value) {
		this.setBFieldLatLng(value);
	},

	getAFieldLatLngValue: function () {
		return this.getAFieldLatLng();
	},

	getBFieldLatLngValue: function () {
		return this.getBFieldLatLng();
	}

});