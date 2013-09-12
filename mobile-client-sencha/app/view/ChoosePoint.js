Ext.define('ChoosePointMenuModel', {
	extend: 'Ext.data.Model',
	config: {
		fields: ['id', 'title']
	}

});

Ext.define('ChoosePointMenuStore', {
	id: 'choosePointMenuStore',
	model: 'ChoosePointMenuModel',
	config: {
		fields: ['title']
	},

	data: [
		{id: '0', title: 'Minha Localização'},
		{id: '1', title: 'Mostrar no mapa'},
		{id: '2', title: 'Favorito'}
	]
});

Ext.define('mobile-client-sencha.view.ChoosePoint', {
    extend: 'Ext.Panel',

    id: 'choosePointView',

    xtype: 'ChoosePoint',

	requires: [
		'Ext.List',
		'Ext.field.Search'
	],

    config: {

	    layout: 'fit',

        items: [

            {
                xtype: 'toolbar',
                docked: 'top',
	            ui: 'light',
                //cls: 'x-toolbar',

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
		                flex: 1,
		                placeHolder: 'Encontrar um ponto'
	                }
                ]
            },

	        {
		    	xtype: 'list',
		        /*store: 'ChoosePointMenuStore',*/
		        itemTpl: '{title}',
		        margin: 10,

		        data: [
			        {
				        title: 'Minha Localizacao'
			        },
			        {
				        title: 'Mostrar no mapa'
			        },
			        {
				        title: 'Favorito'
			        }
		        ]
	        }

        ]
    },

	setSearchFieldPlaceHolder : function(text) {
		var searchField = Ext.ComponentQuery.query('searchfield')[0];
		if (searchField){
			searchField.setPlaceHolder(text);
		}
	}
});