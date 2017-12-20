var exec = require('cordova/exec');


var GlobalBroadcasterPlugin = {
        sendGlBroadcast: function(eventName, data, success, error) {
                   exec(success, error, "GlobalBroadcaster", "sendBroadcast", [eventName, data]);
        }
}

module.exports = GlobalBroadcasterPlugin;
