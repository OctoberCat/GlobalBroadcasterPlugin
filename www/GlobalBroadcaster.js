var exec = require('cordova/exec');


var GlobalBroadcasterPlugin = {
        sendBroadcast: function(eventName, data, success, error) {
                   exec(success, error, "GlobalBroadcaster", "sendBroadcast", [eventName, data]);
        }
}

module.exports = GlobalBroadcasterPlugin;