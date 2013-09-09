Ext.define('mobile-client-sencha.controller.ChoosePointController', {
	extend: 'Ext.app.Controller',

	views: [
		'mobile-client-sencha.view.Home',
		'mobile-client-sencha.view.Routes',
		'mobile-client-sencha.view.ChoosePoint'
	],

	config: {
		control: {
			'button[id=homeBtn1]': {
				tap: "onTapHomeBtn"
			}
		},

		/*control: {
		 '[id=aField]': {
		 tap: "onTapAField"
		 }
		 },*/

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
		}
	},

	onTapHomeBtn: function (button, e, eOpts) {
		Ext.Viewport.getLayout().setAnimation({
			type: 'slide',
			direction: 'right'
		});

		Ext.Viewport.setActiveItem(this.getHomeView());
	}
});