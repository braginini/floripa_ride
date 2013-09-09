Ext.define('mobile-client-sencha.controller.RoutesController', {
    extend: 'Ext.app.Controller',

    views: [
        'mobile-client-sencha.view.Home',
        'mobile-client-sencha.view.Routes',
        'mobile-client-sencha.view.ChoosePoint'
    ],

    config: {

	    refs: {
		    homeView: {
			    autoCreate: true,
			    selector: '#homeView',
			    xtype: 'Home'
		    },

		    routesView: {
			    autoCreate: true,
			    selector: '#routesView',
			    xtype: 'Routes'
		    },

		    choosePointView: {
			    autoCreate: true,
			    selector: '#choosePointView',
			    xtype: 'ChoosePoint'
		    }
	    },

        control: {

            'button[id=homeBtn]': {
                tap: "onTapHomeBtn"
            },

	        'textfield[id=aField]': {
		        tap: 'onTapAField'
	        }

        }

        /*control: {
            '[id=aField]': {
                tap: "onTapAField"
            }
        },*/

    },

    onTapHomeBtn: function (button, e, eOpts) {
        Ext.Viewport.getLayout().setAnimation({
            type: 'slide',
            direction: 'right'
        });

        Ext.Viewport.setActiveItem(this.getHomeView());
    },

    onTapAField: function (button, e, eOpts) {
        Ext.Viewport.getLayout().setAnimation({
            type: 'slide',
            direction: 'left'
        });

        Ext.Viewport.setActiveItem(this.getChoosePointView());
    }
});