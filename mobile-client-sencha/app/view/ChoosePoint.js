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
            }

        ]
    }


});