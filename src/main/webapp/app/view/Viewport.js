Ext.define(appName + '.view.Viewport', {
    extend      : 'Ext.container.Viewport',
    requires    : [ appName + '.view.panel.NorthPanel', appName + '.view.panel.SouthPanel',
        appName + '.view.panel.CenterPanel'],
    layout      : 'border',
    alias       : 'widget.viewport',
    items       : [{
        xtype   : 'northpanel',
        region  : 'north'
    }, {
        xtype   : 'centerpanel',
        region  : 'center'
    }, {
        xtype   : 'southpanel',
        region  : 'south'
    }],
    initComponent   : function() {


        this.callParent(arguments);
    }
});
