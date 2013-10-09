Ext.define('mobile-client-sencha.view.MainNavView', {
	extend: 'Ext.NavigationView',

	id: 'mainNavView',

	xtype: 'MainNavView',

	requires: [
		'mobile-client-sencha.view.RoutesView'
	],

	config: {

		defaultBackButtonText: '',

		navigationBar: {
			ui: 'dark',
			docked: 'top',
			layout: 'fit',
			hideAnimation: Ext.os.is.Android ? false : {
				type: 'fadeOut',
				duration: 200
			},
			showAnimation: Ext.os.is.Android ? false : {
				type: 'fadeIn',
				duration: 200
			},

			backButton: {
				iconCls:'arrow_left',
				iconMask: true,
				ui: 'dark',
				cls: 'backButton'
			},

			items: [
				{
					hidden: true,
					xtype: 'searchfield',
					flex: 1,
					placeHolder: 'Encontrar um ponto',
					id: 'choosePointSearch'
				}
			]
		},

		layout: 'card',
		items: [
			{
				title: 'Como obter?',
				xtype: 'RoutesView'
			}
		]
	}

});