Ext.define(appName + '.model.Book', {
    extend: 'Ext.data.Model',
    idProperty: 'bookId',
    fields: [
        {name: 'bookId', type: 'int'},
        {name: 'author', type: 'string'},
        {name: 'number', type: 'int'},
        {name: 'name', type: 'string'},
        {name: 'year', type: 'int'},
        {name: 'publisherId', type: 'int'},

    ]
});