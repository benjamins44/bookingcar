function fn() {

    var port = karate.properties['local.server.port'];
    var config = {
        baseUrl: 'http://localhost:' + port
    };

    return config;
}