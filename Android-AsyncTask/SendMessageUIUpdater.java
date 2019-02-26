package com.app.chatzone.DBComm.GUI.message;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.app.chatzone.DBComm.GUI.UIUpdater;
import com.app.chatzone.dataStructures.Message;
import com.app.chatzone.fragments.res.ChatMessageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Updates the UI after a message is sent
 */
public class SendMessageUIUpdater implements UIUpdater {

    ChatMessageAdapter listAdapter;
    public SendMessageUIUpdater(ChatMessageAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    /**
     * @param response the result of doInBackground
     */
    @Override
    public void update(JSONObject response) {
        Message message = new Message();
        try {
            if(response != null && response.has("message")) {
                message.handle = response.getJSONObject("message").getString("handle");
                message.id = response.getJSONObject("message").getString("id");
            } else {
                String error = response.getString("error");
                Toast.makeText(listAdapter.getContext(), error, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Log.d("ERROR", e.toString());
        }

        listAdapter.updateHandle(message.handle);
    }
}
