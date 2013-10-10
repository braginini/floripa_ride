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
			/*{
			 xtype: 'fieldset',
			 items: [
			 {
			 xtype: 'textfield',
			 //margin: 10,
			 component: {
			 xtype: 'container',
			 layout: 'hbox',
			 items: [
			 {
			 xtype: 'textfield',
			 placeHolder: 'A: Especificar o ponto de partida',
			 align: 'center',
			 name: 'firstName',
			 id: 'aField',
			 readOnly: true,
			 flex: 4,

			 listeners: {
			 element: 'element',
			 tap: function () {
			 this.fireEvent('tap');
			 }
			 }
			 },
			 {
			 xtype: 'label',
			 html: '<span style="float:right; margin-right:10px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAANCAYAAACQN/8FAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAALRJREFUeNpi/P//P8OkSZMkGBgYtgNxfV5e3iYGLIBZSEhIGkjvB2Id IA4+efLkJXNz85voCpmgNBsSvRpogx+GQqBVT4G0LRDfx6cYbCIexd7oViMrvoOkeB1MMSPI18gA KGEPpA4gCYFs0WBCU6QCpJYiCYFscQPa9osJSZE61CRpJEW2QEV34G6EKtqLpOgRVNF95ADHpsgB WRHMxI9A/AWfIliAvwBJAvFudOuQAUCAAQDiG0runJxCpwAAAABJRU5ErkJggg=="/></span>',
			 padding: '10 0 0 0',
			 flex: 1
			 }
			 ]

			 }
			 },

			 {
			 xtype: 'textfield',
			 //margin: 10,
			 component: {
			 xtype: 'container',
			 layout: 'hbox',
			 items: [
			 {
			 xtype: 'textfield',
			 placeHolder: 'B: Especificar o ponto final',
			 align: 'center',
			 name: 'firstName',
			 id: 'bField',
			 readOnly: true,
			 flex: 4,

			 listeners: {
			 element: 'element',
			 tap: function () {
			 this.fireEvent('tap');
			 }
			 }
			 },

			 {
			 xtype: 'label',
			 html: '<span style="float:right; margin-right:10px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAANCAYAAACQN/8FAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAALRJREFUeNpi/P//P8OkSZMkGBgYtgNxfV5e3iYGLIBZSEhIGkjvB2Id IA4+efLkJXNz85voCpmgNBsSvRpogx+GQqBVT4G0LRDfx6cYbCIexd7oViMrvoOkeB1MMSPI18gA KGEPpA4gCYFs0WBCU6QCpJYiCYFscQPa9osJSZE61CRpJEW2QEV34G6EKtqLpOgRVNF95ADHpsgB WRHMxI9A/AWfIliAvwBJAvFudOuQAUCAAQDiG0runJxCpwAAAABJRU5ErkJggg=="/></span>',
			 padding: '10 0 0 0',
			 flex: 1
			 }
			 ]

			 }
			 }
			 ]
			 },*/

			{
				xtype: 'segmentedbutton',
				allowDepress: true,
				margin: 15,
				items: [

					{
						pressed: true,
						iconCls: 'team',
						iconMask: true
					},
					{
						iconCls: 'team',
						iconMask: true
					},
					{
						iconCls: 'team',
						iconMask: true
					}
				]
			},

			{
				xtype: 'container',
				layout: 'vbox',
				items: [
					{
						xtype: 'selectfield',
						margin: 15,
						id: 'departField',
						options: [
							{text: 'Depart', value: 0},
							{text: 'Arrive', value: 1}
						]
					},

					{
						xtype: 'timepickerfield',
						value: new Date(),
						id: 'timeField',
						margin: 15
					},

					{
						xtype: 'datepickerfield',
						value: new Date(),
						id: 'dateField',
						margin: 15
					}
				]
			},

			{
				xtype: 'button',
				text: 'Ok',
				margin: 15,
				id: 'searchRouteBtn'
			},
			{
				xtype: 'list',
				id: 'itineraries',
				itemTpl: '{startTime} - {endTime} {transfers} transfers {duration}',
				margin: 15,
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