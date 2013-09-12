Ext.define('mobile-client-sencha.controller.HomeController', {
	extend: 'Ext.app.Controller',

	views: [
		'mobile-client-sencha.view.Home',
		'mobile-client-sencha.view.Routes'
	],

	config: {
		control: {
			'button[id=routesBtn]': {
				tap: "onTapRoutesBtn"
			},

			'button[id=mapBtn]': {
				tap: function () {
				}
			}
		},

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
			}
		}
	},

	onTapRoutesBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'left'
		});

		Ext.Viewport.setActiveItem(this.getRoutesView());
	},


});