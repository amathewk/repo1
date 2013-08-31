package com.example.TestApp;

import android.util.Log;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class EchoPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        boolean validAction = false;

        if ("echo".equals(action)) {
            String message = args.getString(0);
            echo(message, callbackContext);
            validAction = true;
        } else {
            Log.e("EchoPlugin", "Invalid action : " + action);
        }

        return validAction;
    }

    private void echo(String message, CallbackContext context) {

        if (message != null && message.trim().length() > 0) {
            context.success(message);
        } else {
            context.error("No content in message");
        }
    }



}
