Ext.define(appName + '.view.publisher.PublisherForm', {
    extend: 'Ext.form.Panel',
    id: 'myformpublisher',
    alias: 'widget.publisherform',
    title: ' Publisher Management',
    glyph: 'xE7EE@Material Icons',
    autoScroll: true,
    monitorValid: true,
    defaultType: 'textfield',
    buttonAlign: 'center',
    fieldDefaults: {

        labelWidth: 150,
        labelStyle: 'font-weight:bold;',
        allowBlank: false,
        anchor: '%95',
        msgTarget: 'under',
        padding: '10 0 0 15'
    },

    initComponent: function () {
        this.items = [
            {
                xtype: 'hidden',
                name: 'publisherId'
            },
            {
                fieldLabel: 'Publisher Name',
                name: 'publisherName'
            },
            {
                fieldLabel: 'Address',
                xtype : 'textareafield',
                name: 'publisherAddress'
            },
            {
                fieldLabel: 'Phone Number',
                name: 'publisherPhone',
                hideTrigger: true,
                xtype: 'numberfield',
                allowDecimals: false

            }
            ];


        this.btnSavePublisher = Ext.create('Ext.Button', {
            text: 'Save',
            glyph: 'xE161@Material Icons',
            formBind: true,
            action: 'savePublisher'
        });
        this.buttons = [this.btnSavePublisher];
        this.callParent(arguments);

    }

});