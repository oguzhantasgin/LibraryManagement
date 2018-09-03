Ext.define(appName + '.view.book.BookGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.bookgrid',
    title: 'Book List',
    glyph: 'xE02F@Material Icons',
    store: 'Books',
    forceFit: true,
    border: true,
    autoScroll: true,
    headerBorders: false,
    viewConfig: {
        stripeRows: true,
        emptyText: '<span class="x-grid-empty">Book list is empty.</span>',
        enableTextSelection: false,
        loadMask: false
    },
    selModel: 'rowmodel',
    initComponent: function () {

        var me = this;

        this.selType = 'rowmodel';

        this.columns = [{

            xtype: 'rownumberer',
            align: 'center'
        }, {
            header: 'Book Name',
            dataIndex: 'bookName',
            sortable: false,
            flex: 1
        }, {
            header: 'Author',
            dataIndex: 'bookAuthor',
            sortable: false,
            flex: 1
        }, {
            header: 'Book Number',
            dataIndex: 'bookNumber',
            sortable: false,
            flex: 1
        }, {
            header: 'Book Date',
            dataIndex: 'bookYear',
            sortable: false,
            flex: 1
        }, {
            xtype: 'actioncolumn',
            align: 'center',
            sortable: false,
            menuDisabled: true,
            items: [{
                iconCls: 'icon-delete',
                tooltip: 'Delete',
                handler: function (grid, rowIndex, colIndex, node, e, record, rowNode) {
                    var action = 'delete';
                    this.fireEvent('itemclick', this, action, grid, rowIndex, colIndex, record, node);
                }
            }]
        }];

        this.callParent(arguments);
    }
});