// noinspection JSAnnotator
Ext.define(appName + '.model.Book', {
    extend: 'Ext.data.Model',
    idProperty: 'bookId',
    fields: [
        {name: 'bookId', type: 'int'},
        {name: 'bookNumber', type: 'int'},
        {name: 'bookAuthor', type: 'string'},
        {name: 'bookName', type: 'string'},
        {name: 'bookYear', type: 'string'},
        {name: 'publisherId', type: 'int' , allowNull: false }

    ]
});