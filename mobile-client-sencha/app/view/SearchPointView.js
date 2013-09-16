Ext.define('mobile-client-sencha.view.SearchPointView', {
	extend: 'Ext.Panel',

	id: 'searchPointView',

	xtype: 'SearchPointView',

	config: {

		items: [

			{
				xtype: 'toolbar',
				docked: 'top',
				cls: 'x-toolbar',

				items: [
					{
						xtype: 'button',
						iconCls: 'home',
						iconMask: true,
						ui: 'action',
						id: 'homeBtn1'
					},

					{
						xtype: 'searchfield',
						placeHolder: ''
					}
				]
			}
		]
	}
});