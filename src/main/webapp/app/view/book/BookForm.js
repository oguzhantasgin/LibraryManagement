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
                name: 'bookName'
            },
            {
                fieldLabel: 'Author',
                name: 'bookAuthor'
            },
            {
                fieldLabel: 'Book Number',
                name: 'bookNumber',
                hideTrigger: true,
                xtype: 'numberfield',
                allowDecimals: false,
                minValue: 1
            },
            {
                fieldLabel: 'Book Date',
                name: 'bookYear',
                xtype: 'datefield',
                format: 'd/m/Y'

            },
            {
                fieldLabel: 'Publisher',
                name: 'publisherId',
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