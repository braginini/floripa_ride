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
		        itemTpl: '{title}<span style="float:right; margin-right:10px;"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAANCAYAAACQN/8FAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAALRJREFUeNpi/P//P8OkSZMkGBgYtgNxfV5e3iYGLIBZSEhIGkjvB2Id IA4+efLkJXNz85voCpmgNBsSvRpogx+GQqBVT4G0LRDfx6cYbCIexd7oViMrvoOkeB1MMSPI18gA KGEPpA4gCYFs0WBCU6QCpJYiCYFscQPa9osJSZE61CRpJEW2QEV34G6EKtqLpOgRVNF95ADHpsgB WRHMxI9A/AWfIliAvwBJAvFudOuQAUCAAQDiG0runJxCpwAAAABJRU5ErkJggg=="/></span>',
		        /*margin: 10,*/
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