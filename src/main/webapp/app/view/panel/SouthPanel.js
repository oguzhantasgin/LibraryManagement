Ext.define(appName + '.view.panel.SouthPanel', {
    preventHeader: true,
    header      : false,
    glyph       : 'xE851@Material Icons',
    extend      : 'Ext.panel.Panel',
    alias       : 'widget.southpanel',
    layout      : {
        type    : 'hbox',
        align   : 'center',
        pack    : 'center'
    },
    initComponent: function() {

        this.items = [{
            xtype       : 'displayfield',
            value       : 'Oğuzhan TAŞGIN EXTJS-Spring MVC-Hibernate'
        }];

        this.callParent(arguments);
    }
});