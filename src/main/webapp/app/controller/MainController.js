Ext.define(appName + '.controller.MainController', {
    extend: 'Ext.app.Controller',
    models: ['Book', 'Publisher'],
    stores: ['Books', 'Publishers'],
    views: ['publisher.PublisherCombo', 'book.BookForm', 'book.BookGrid', 'publisher.PublisherForm'],
    refs: [{
        ref: 'bookGrid',
        selector: 'bookgrid'
    }, {
        ref: 'bookForm',
        selector: 'bookform'
    }, {
        ref: 'publisherForm',
        selector: 'publisherform'

        }],

    init: function () {
        this.control({
            'bookgrid': {
                viewready: this.gridViewReady,
                selectionchange: this.bookGridSelectionChange
            },
            'bookgrid actioncolumn': {
                itemclick: this.bookGridActions
            },
            'bookform button[action=save]': {
                click: this.saveOrUpdateBook
            },
            'publisherform button[action=savePublisher]': {
                click: this.saveOrUpdatePublisher
            }
        });
    },

    gridViewReady: function (grid) {
        grid.getStore().load();
    },
    bookGridSelectionChange: function (selModel, selections, eOpts) {
        var bookForm = this.getBookForm();
        if (selections.length > 0) {
            var record = selections[0];
            bookForm.getForm().loadRecord(record);

        }
    },
    bookGridActions: function (column, action, grid, rowIndex, colIndex, record, node) {
        if (action == 'delete') {
            Ext.Msg.confirm('Sure?', 'Are you sure for <span style="font-weight: bold;">delete</span> ?', function (btn) {
                if (btn == 'yes') {
                    Ext.Ajax.request({
                        url: 'deleteBook.ajax',
                        method: 'POST',
                        params: {
                            bookId: record.get('bookId')
                        },
                        success: function (response, options) {
                            var res = Ext.decode(response.responseText);
                            if (res.success) {
                                grid.getStore().reload();
                            }
                        }
                    });
                }
            });
        }
    },
    saveOrUpdateBook: function (btn) {
        var grid = this.getBookGrid();
        var form = btn.up('form');
        Ext.Ajax.request({
            url: 'saveOrUpdateBook.ajax',
            method: 'POST',
            params: {
                data: Ext.encode(form.getForm().getValues())
            },
            success: function (response, options) {
                var res = Ext.decode(response.responseText);
                if (res.success) {
                    Ext.Msg.show({
                        title: 'Success',
                        msg: 'Book register done.',
                        icon: Ext.Msg.INFO,
                        buttons: Ext.Msg.OK
                    });
                    grid.getStore().reload();
                    form.getForm().reset();
                }
            }
        });
    },
    saveOrUpdatePublisher: function (btn) {

        var form = btn.up('form');
        Ext.Ajax.request({
            url: 'saveOrUpdatePublisher.ajax',
            method: 'POST',
            params: {
                data: Ext.encode(form.getForm().getValues())
            },
            success: function (response, options) {
                var res = Ext.decode(response.responseText);
                if (res.success) {
                    Ext.Msg.show({
                        title: 'Success',
                        msg: 'Publisher register done.',
                        icon: Ext.Msg.INFO,
                        buttons: Ext.Msg.OK
                    });
                    grid.getStore().reload();
                    form.getForm().reset();

                }
            }
        });
    }


});