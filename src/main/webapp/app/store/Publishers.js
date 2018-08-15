Ext.define(appName + '.store.Publishers', {
    extend		: 'Ext.data.Store',
    model		: appName + '.model.Publisher',
    autoLoad	: false,
    autoSync	: false,
    remoteSort	: false,
    proxy		: {
        type	: 'ajax',
        api		: {
            read	: 'loadPublisher.ajax'
        },
        reader	: {
            type	        : 'json',
            rootProperty	: 'data',
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