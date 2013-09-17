Ext.define('mobile-client-sencha.controller.RoutesController', {
    extend: 'Ext.app.Controller',

    config: {

	    views: [
		    'mobile-client-sencha.view.HomeView',
		    'mobile-client-sencha.view.RoutesView',
		    'mobile-client-sencha.view.ChoosePointView'
	    ],

	    refs: {
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
	}
});