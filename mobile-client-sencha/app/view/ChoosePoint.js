Ext.define('mobile-client-sencha.view.ChoosePoint', {
    extend: 'Ext.Panel',

    id: 'choosePointView',

    xtype: 'ChoosePoint',

    config: {

        items: [

            {
                xtype: 'toolbar',
                docked: 'top',
                cls: 'x-toolbar',
                title: 'Como obter?',

                items: [
                    {
                        xtype: 'searchfield',
                        placeHolder: 'Encontrar um ponto'
                    }
                ]

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