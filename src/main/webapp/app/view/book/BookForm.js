Ext.define(appName + '.view.book.BookForm', {
    extend: 'Ext.form.Panel',
    id: 'myform',
    alias: 'widget.bookform',
    title: ' Library Management Form',
    glyph: 'xE87C@Material Icons',
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
                name: 'bookId'
            },
            {
                fieldLabel: 'Book Name',
                name: 'name'
            },
            {
                fieldLabel: 'Author',
                name: 'author'
            },
            {
                fieldLabel: 'Book Number',
                name: 'number',
                hideTrigger: true,
                xtype: 'numberfield',
                allowDecimals: false,
                minValue: 1
            },
            {
                fieldLabel: 'Book Date',
                name: 'year',
                xtype: 'datefield',
                format: 'd/m/Y'

            },
            {
                fieldLabel: 'Publisher',
                name: 'bookPublisherName',
                xtype: 'publishercombo',
                allowblank: false
            }

            ];


        this.btnSave = Ext.create('Ext.Button', {
            text: 'Save',
            glyph: 'xE161@Material Icons',
            formBind: true,
            action: 'save'
        });

        this.buttons = [this.btnSave];
        this.callParent(arguments);

    }

});