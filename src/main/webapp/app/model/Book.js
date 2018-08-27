// noinspection JSAnnotator
Ext.define(appName + '.model.Book', {
    extend: 'Ext.data.Model',
    idProperty: 'bookId',
    fields: [
        {name: 'bookId', type: 'int'},
        {name: 'number', type: 'int'},
        {name: 'author', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'bookPublisherName', type: 'string'},
        {name: 'year', type: 'string'},
        {name: 'bookPublisherId', type: 'int' , allowNull: false }

    ]
});