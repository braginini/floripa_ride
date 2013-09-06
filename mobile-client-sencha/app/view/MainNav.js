Ext.define('mobile-client-sencha.view.MainNav', {
	extend: 'Ext.navigation.View',

	requires: [
		'Ext.field.Search',
		'Ext.TitleBar',
		'mobile-client-sencha.view.Home'
	],

	config: {

		navigationBar : {
			backButton : {
				align   : 'left',
				hidden  : true,
				iconCls: 'home',
				iconMask: true
			}
		},

		items: [
			{
				xtype: 'home'
			}
		]

	}
});
