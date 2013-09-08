Ext.define('mobile-client-sencha.view.Home', {
	extend: 'Ext.Panel',

	xtype: 'Home',

	id: 'homeView',

	config: {

		items: [
			{
				xtype: 'toolbar',
				docked: 'top',
				cls: 'x-toolbar'
				//ui: 'light'
			},
			{
				xtype: 'button',
				text: 'Mapa',
				cls: 'custom-button default',
				id: 'mapBtn'
			},
			{
				xtype: 'button',
				text: 'Rotas',
				cls: 'custom-button default',
				id: 'routesBtn'
			}
		]

	}
});