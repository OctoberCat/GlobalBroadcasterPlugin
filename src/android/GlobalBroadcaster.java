package com.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import android.content.Context;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;



public class GlobalBroadcaster extends CordovaPlugin {
    private static final String TAG = GlobalBroadcaster.class.getSimpleName();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
            throws JSONException {


        if (action.equals("sendBroadcast")) {
            Log.d(TAG, "execute: sendBroadcast");
            final String eventName =  (String) args.get(0);
            if (eventName == null || eventName.isEmpty()) {
                callbackContext.error("event name null or empty.");
            }
            final JSONObject userData = args.getJSONObject(1);

            this.cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    sendBroadcast(eventName, userData);
                }
            });
            return true;
        }
        return false;
    }

    protected void sendBroadcast(String eventName, JSONObject data) {
        Context context = this.cordova.getActivity().getApplicationContext();
        Intent intent = new Intent(eventName);
        intent.putExtra("userdata", data.toString());
        context.sendBroadcast(intent);
    }
}