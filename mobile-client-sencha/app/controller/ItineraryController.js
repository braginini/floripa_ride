Ext.define('mobile-client-sencha.controller.ItineraryController', {
	extend: 'Ext.app.Controller',

	config: {

		views: [
			'mobile-client-sencha.view.RoutesView',
			'mobile-client-sencha.view.ItineraryView'
		],

		refs: {

			itineraryLegList: 'list[id=itineraryLegList]',

			routesView: {
				autoCreate: false,
				selector: '[id=routesView]',
				xtype: 'RoutesView'

			},

			itineraryView: {
				autoCreate: true,
				selector: '[id=itineraryView]',
				xtype: 'ItineraryView'
			}
		},

		control: {

			itineraryView: {
				activate: 'onShowItineraryView'
			},

			itineraryLegList: {
				itemtap: function (list, idx, target, record, evt) {
					setTimeout(function () {
						list.deselect(idx);
					}, 100);

					this.onTapLegList(list, idx, target, record, evt)
				}
			}
		}
	},

	onTapLegList: function(list, idx, target, record, evt) {
		console.log(record.getData().isLeg);
	},

	onShowItineraryView: function () {
		var itinerary = this.getItineraryView().getItinerary();
		var plan = this.getItineraryView().getPlan();
		var itineraryLegList = this.getItineraryLegList();

		if (itinerary && itineraryLegList) {

			itineraryLegList.getStore().getData().clear();

			var fromPoint = Ext.create('mobile-client-sencha.model.PointModel');
			var toPoint = Ext.create('mobile-client-sencha.model.PointModel');
			fromPoint.setData(plan.from);
			toPoint.setData(plan.to);
			fromPoint.getData().isLeg = false;
			toPoint.getData().isLeg = false;

			itineraryLegList.getStore().add(fromPoint);

			for (var i = 0; i < itinerary.legs.length; i++) {
				itineraryLegList.getStore().add(itinerary.legs[i]);
			}

			itineraryLegList.getStore().add(toPoint);
		}
	}
});