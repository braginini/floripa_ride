Ext.define('mobile-client-sencha.view.Routes', {
	extend: 'Ext.Panel',

	/*alias: 'widget.routes',*/

	id: 'routesView',

	xtype: 'Routes',

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
				text: 'Btn',
				cls: 'custom-button default'
			}
		]
	}


});