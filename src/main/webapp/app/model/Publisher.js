Ext.define(appName + '.model.Publisher', {
    extend: 'Ext.data.Model',
    idProperty: 'publisherId',
    fields: [
        {name: 'publisherId', type: 'int'},
        {name: 'publisherName', type: 'string'},
        {name: 'publisherAddress', type: 'string'},


    ]
});