Ext.define('mobile-client-sencha.controller.RoutesController', {
    extend: 'Ext.app.Controller',

    config: {

	    views: [
		    'mobile-client-sencha.view.HomeView',
		    'mobile-client-sencha.view.RoutesView',
		    'mobile-client-sencha.view.ChoosePointView'
	    ],

	    refs: {

		    searchRouteBtn: '#searchRouteBtn',
		    aField: '#aField',
		    bField: '#bField',

		    homeView: {
			    autoCreate: true,
			    selector: '#homeView',
			    xtype: 'HomeView'
		    },

		    routesView: {
			    autoCreate: true,
			    selector: '#routesView',
			    xtype: 'RoutesView'
		    },

		    choosePointView: {
			    autoCreate: true,
			    selector: '#choosePointView',
			    xtype: 'ChoosePointView'
		    }
	    },

        control: {

            'button[id=homeBtn]': {
                tap: 'onTapHomeBtn'
            },

	        'textfield[id=aField]': {
		        tap: function() {
			    	this.onTapField('aField')
		        }
	        },

	        'textfield[id=bField]': {
		        tap: function() {
			        this.onTapField('bField')
		        }
	        } ,

	        searchRouteBtn: {
		        tap: 'onSearchRouteBtnTap'
	        }
        }

    },

    onTapHomeBtn: function (button, e, eOpts) {
        Ext.Viewport.getLayout().setAnimation({
            type: 'slide',
            direction: 'right'
        });

        Ext.Viewport.setActiveItem(this.getHomeView());
    },

	onTapField: function (field) {

		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		var choosePointView = this.getChoosePointView();
		var text = '';
		var isAFieldTapped = false;
		switch(field) {
			case 'aField' :
				text = 'Encontrar o ponto de partida';
				isAFieldTapped = true;
				break;
			case 'bField' :
				text = 'Encontrar o ponto final';
				isAFieldTapped = false;
				break;
			default :
				text = 'Encontrar um ponto'
				isAFieldTapped = false;
				break;
		}

		choosePointView.setSearchFieldPlaceHolder(text);
		choosePointView.setFieldTapped(isAFieldTapped);
		Ext.Viewport.setActiveItem(choosePointView);
	},

	onSearchRouteBtnTap : function() {

		if (this.getAField().getValue().length != 0 && this.getBField().getValue().length != 0) {

			Ext.data.JsonP.request({
				url: 'http://localhost:8080/opentripplanner-api-webapp/ws/plan',
				callbackKey: 'callback',
				async: false,
				timeout: 5000,
				params: {
					_dc: Date.now(),
					fromPlace: this.getAField().getValue(),
					toPlace: this.getBField().getValue(),
					ui_date: '9/17/2013',
					arriveBy: 'false',
					time: '5:10pm',
					mode: 'TRANSIT,WALK',
					optimize: 'QUICK',
					maxWalkDistance: '840',
					date: '2013-09-17',
					walkSpeed: '1.341',
					numItineraries: '6'
				},

				success: function(result, request) {
					//todo handle errors sent by server
					alert(result.plan.itineraries.length + " itineraries found");
				},

				failure: function(result, request) {
					alert("failed");
				}
			});
		}
	}
});