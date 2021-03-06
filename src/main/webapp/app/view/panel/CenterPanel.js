Ext.define(appName + '.view.panel.CenterPanel', {
    header: false,
    glyph: 'xE851@Material Icons',
    extend: 'Ext.panel.Panel',
    alias: 'widget.centerpanel',
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    initComponent: function () {


        this.formP = Ext.create(appName + '.view.publisher.PublisherForm', {
            flex: 1
        });
        this.form = Ext.create(appName + '.view.book.BookForm', {
            flex: 1
        });
        this.panel = Ext.create(appName + '.view.book.BookGrid', {
            flex: 2
        });

        this.items = [this.form, this.panel,this.formP];

        this.callParent(arguments);
    }
});