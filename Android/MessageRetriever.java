package com.app.chatzone.DBComm.query.message;

import android.net.Uri;
import android.util.Log;

import com.app.chatzone.DBComm.query.DBComm;
import com.app.chatzone.DBComm.query.DataBaseAdapter;
import com.app.chatzone.DBComm.util.JSON;
import com.app.chatzone.dataStructures.Message;
import com.app.chatzone.dataStructures.Pointer;
import com.app.chatzone.dataStructures.Zone;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageRetriever implements DBComm {

    private final String GET_MESSAGE = "/messages";

    Zone zone;
    String userId;
    LatLng position;
    Pointer<String> lastMessageId;

        /**
     * Retrieves messages from the server
     *
     * @param zone
     * the zone to check messages for
     * @param userId
     * the user requesting messages
     * @param position
     * the users current {@code LatLng} location
     * @param lastMessageId
     * the message id of the newest message
     * @return
     * All messages newer then {@code lastMessageId}
     * or all messages in the {@code zone} if {@code lastMessageId} is {@code null}
     *
     */
    public MessageRetriever(Zone zone, String userId, LatLng position, Pointer<String> lastMessageId) {
        this.zone = zone;
        this.userId = userId;
        this.position = position;
        this.lastMessageId = lastMessageId;
    }

    /**
     * Updates {@code lastMessageId}
     *
     * @return
     * All messages newer then {@code lastMessageId}
     * or all messages in the {@code zone} if {@code lastMessageId} is {@code null}
     *
     */
    public JSONObject result() {
        JSONObject result;
        synchronized (lastMessageId) {
            String url = Uri.parse(GET_MESSAGE).buildUpon()
                    .appendQueryParameter("zone_id", zone.id)
                    .appendQueryParameter("user_id", userId)
                    .appendQueryParameter("latitude", String.valueOf(position.latitude))
                    .appendQueryParameter("longitude", String.valueOf(position.longitude)).toString();
            if (lastMessageId.get() != null)
                url = Uri.parse(url).buildUpon()
                        .appendQueryParameter("message_id", String.valueOf(lastMessageId.get())).toString();

            result = new DataBaseAdapter().get(url);
            try {
                JSONArray messages = result.getJSONArray("messages");
                if(messages.length() > 0) {
                    Message message = JSON.getMessage(messages.getJSONObject(0));
                    this.lastMessageId.set(message.id);
                }
            } catch (JSONException e) {
                Log.d("ERROR", e.toString());
            }
        }
        return result;
    }
}
