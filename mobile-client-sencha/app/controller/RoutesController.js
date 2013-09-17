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
			Ext.Ajax.request({
				url: 'http://localhost:8080/opentripplanner-api-webapp/ws/plan?_dc=1379448679492&arriveBy=false&time=5%3A10pm&ui_date=9%2F17%2F2013&mode=TRANSIT%2CWALK&optimize=QUICK&maxWalkDistance=840&walkSpeed=1.341&date=2013-09-17&toPlace=-27.575587%2C-48.541124&fromPlace=-27.583955%2C-48.522842',
				timeout: 60000,
				success: function(response){
					console.log(response.responseText);
				}
			});
		}
	}
});