Ext.define(appName + '.store.Books', {
    extend		: 'Ext.data.Store',
    model		: appName + '.model.Book',
    autoLoad	: false,
    autoSync	: false,
    remoteSort	: false,
    proxy		: {
        type	: 'ajax',
        api		: {
            read	: 'loadBooks.ajax'
        },
        reader	: {
            type	        : 'json',
            rootProperty    : 'data',
            successProperty : 'success',
            totalProperty	: 'totalCount'
        },
        writer	: {
            type	        : 'json',
            writeAllFields  : true,
            encode	        : true,
            rootProperty	: 'data'
        }
    }
});