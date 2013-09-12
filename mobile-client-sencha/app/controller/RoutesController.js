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
		switch(field) {
			case 'aField' :
				text = 'Encontrar o ponto de partida';
				break;
			case 'bField' :
				text = 'Encontrar o ponto final';
				break;
			default :
				text = 'Encontrar um ponto'
				break;
		}

		choosePointView.setSearchFieldPlaceHolder(text);
		Ext.Viewport.setActiveItem(choosePointView);
	}
});