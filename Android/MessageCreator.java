package com.app.chatzone.DBComm.query.message;

import android.util.Log;

import com.app.chatzone.DBComm.query.DBComm;
import com.app.chatzone.DBComm.query.DataBaseAdapter;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Used to result a {@code Message} to the server
 */
public class MessageCreator implements DBComm {

    private final String SEND_MESSAGE = "/message";
    /**
     * returns the user handle if one does not exist
     */
    String handle;
    String message;
    String zoneId;
    String userId;
    LatLng location;

    public MessageCreator(String handle, String message, String zoneId, String userId, LatLng location) {
        this.handle = handle;
        this.message = message;
        this.zoneId = zoneId;
        this.userId = userId;
        this.location = location;
    }

    public JSONObject result() {
        JSONObject m = new JSONObject();
        try {
            m.put("content", message);
            m.put("handle", handle);
            m.put("zone_id", zoneId);
            m.put("user_id", userId);
            m.put("latitude", location.latitude);
            m.put("longitude", location.longitude);

        } catch (JSONException e) {
            Log.d("ERROR", e.toString());
        }

        return new DataBaseAdapter().post(m, SEND_MESSAGE);
    }
}
