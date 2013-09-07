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
                cls: 'x-toolbar',
                title: 'Como obter?'
                //ui: 'light'
            },


            {
                xtype: 'textfield',
                placeHolder: 'A: Especificar o ponto de partida',
                name: 'firstName',
                margin: 15
            },
            {
                xtype: 'textfield',
                placeHolder: 'B: Especificar o ponto final',
                name: 'lastName',
                margin: 15
            },
            {
                xtype: 'segmentedbutton',
                allowDepress: true,
                margin: 15,
                items: [

                    {
                        pressed: true,
                        iconCls: 'team',
                        iconMask: true
                    },
                    {
                        iconCls: 'team',
                        iconMask: true
                    },
                    {
                        iconCls: 'team',
                        iconMask: true
                    }
                ]
            }

        ]
    }


});