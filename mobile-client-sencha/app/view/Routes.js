Ext.define('mobile-client-sencha.view.Routes', {
    extend: 'Ext.Panel',

    id: 'routesView',

    xtype: 'Routes',

    config: {

        items: [

            {
                xtype: 'toolbar',
                docked: 'top',
                cls: 'x-toolbar',
                title: 'Como obter?',
                /*iconCls: 'home',
                iconMask: true*/
                items: [
                    {
                        xtype: 'button',
                        iconCls: 'home',
                        iconMask: true,
                        ui: 'action',
                        id: 'homeBtn'
                    }
                ]
                //ui: 'light'
            },


            {
                xtype: 'textfield',
                placeHolder: 'A: Especificar o ponto de partida',
                name: 'firstName',
                margin: 15,
                id: 'aField'
            },
            {
                xtype: 'textfield',
                placeHolder: 'B: Especificar o ponto final',
                name: 'lastName',
                margin: 15,
                id: 'bField'
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