Ext.define('mobile-client-sencha.view.ItineraryView', {
	extend: 'Ext.Panel',

	id: 'itineraryView',

	xtype: 'ItineraryView',

	requires: [
		'Ext.SegmentedButton',
		'Ext.ux.field.TimePicker'
	],

	config: {

		itinerary: null, //the itinerary object

		plan: null,

		layout: 'fit',

		items: [

			{
				xtype: 'list',
				id: 'itineraryLegList',
				store: 'ItineraryListStore',
				itemTpl: new Ext.XTemplate(
					'<tpl if="isLeg == true">LEG',
					'<tpl else>POINT',
					'</tpl>'
				)
			}

		]
	},

	setItinerary: function (itinerary) {
		if (itinerary)
			this.itinerary = itinerary;
	},

	setPlan: function (plan) {
		if (plan)
			this.plan = plan;
	},

	getItinerary: function () {
		return this.itinerary;
	},

	getPlan: function () {
		return this.plan;
	}

});