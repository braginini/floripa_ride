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