Ext.define('mobile-client-sencha.view.Home', {
	extend: 'Ext.Panel',

	xtype: 'Home',

	id: 'homeView',

	config: {

		items: [
			{
				xtype: 'toolbar',
				docked: 'top',
				ui: 'light'
				//cls: 'x-toolbar'
			},
			{
				xtype: 'button',
				text: 'Mapa',
				margin: 15,
				//cls: 'custom-button default',
				id: 'mapBtn'
			},
			{
				xtype: 'button',
				text: 'Rotas',
				//cls: 'custom-button default',
				margin: 15,
				id: 'routesBtn'
			}
		]

	}
});