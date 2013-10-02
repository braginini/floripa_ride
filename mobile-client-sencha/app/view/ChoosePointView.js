Ext.define('mobile-client-sencha.view.ChoosePointView', {
    extend: 'Ext.Panel',

    id: 'choosePointView',

    xtype: 'ChoosePointView',

	requires: [
		'Ext.List',
		'Ext.field.Search'
	],

    config: {

	    layout: 'fit',

	    isAFieldTapped: true,

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
		        itemTpl: '{title}',
		        margin: 10,
		        id: 'choosePointMenu',

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
	},

	isAFieldTapped : function() {
		return this.getIsAFieldTapped();
	},

	setFieldTapped : function(bool) {
		this.setIsAFieldTapped(bool);
	}

});