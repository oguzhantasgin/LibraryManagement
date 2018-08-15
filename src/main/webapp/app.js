Ext.Loader.setConfig({enabled: true});

Ext.application({
    name        : appName,
    appFolder   : 'app',
    controllers : [ 'MainController' ],
    requires    : [ appName + '.view.Viewport' ],

    launch      : function() {

        var viewport = Ext.create(appName + '.view.Viewport');
        viewport.show();

    }
});