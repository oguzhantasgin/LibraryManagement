Ext.define(appName + '.view.publisher.PublisherCombo', {
    extend		: 'Ext.form.field.ComboBox',
    alias		: 'widget.publishercombo',
    triggerAction:  'all',
    forceSelection: true,
    editable    : false,
    queryMode	: 'local',
    displayField: 'publisherName',
    valueField	: 'publisherId',
    store       : 'Publishers',
    initComponent: function() {

        this.on('added', function(combo, container, pos, eOpts) {
            combo.getStore().load();
        });

        this.callParent(arguments);
    }
});