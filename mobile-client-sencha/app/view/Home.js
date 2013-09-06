Ext.define('mobile-client-sencha.view.Home', {
	extend: 'Ext.Panel',

	/*alias: 'widget.home',*/
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
				alias: 'widget.homeMapBtn',
				id: 'homeMapBtn'
			},
			{
				xtype: 'button',
				text: 'Rotas',
				cls: 'custom-button default',
				id: 'homeRoutesBtn',
				action: 'homeRoutesBtn'

				/*listeners: {
					tap: function() {
						this.parent.parent.push(Ext.create('mobile-client-sencha.view.Routes'));
					}
				}*/
			}
		]

	}
});