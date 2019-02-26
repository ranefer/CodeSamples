package com.app.chatzone.DBComm.GUI.message;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.chatzone.DBComm.GUI.UIUpdater;
import com.app.chatzone.DBComm.util.JSON;
import com.app.chatzone.activities.MainActivity;
import com.app.chatzone.dataStructures.Message;
import com.app.chatzone.dataStructures.Zone;
import com.app.chatzone.fragments.res.ChatMessageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Updates the UI after Messages have been recieved
 */
public class RecieveMessageUIUpdater implements UIUpdater {

    ChatMessageAdapter listAdapter;
    TextView emptyMessage;
    MainActivity mainActivity;
    Zone zone;
    public RecieveMessageUIUpdater(ChatMessageAdapter listAdapter, TextView emptyMessage, MainActivity mainActivity, Zone zone) {
        this.listAdapter = listAdapter;
        this.emptyMessage = emptyMessage;
        this.mainActivity = mainActivity;
        this.zone = zone;
    }

    @Override
    public void update(JSONObject response) {

        ArrayList<Message> messages = new ArrayList<Message>();
        ArrayList<Message> bannedMessage = new ArrayList<Message>();
        try {
            JSONArray messagesArray = response.getJSONArray("messages");
            for (int i = 0; i < messagesArray.length(); i++) {
                Message message = JSON.getMessage(messagesArray.getJSONObject(i));
                messages.add(message);
            }
            JSONArray bannedMessageIdArray = response.getJSONArray("banned_messages");
            for(int i = 0; i < bannedMessageIdArray.length(); i++) {
                Message m = new Message();
                m.id = bannedMessageIdArray.getString(i);
                bannedMessage.add(m);
            }
            zone.totalUsers = response.getInt("total_users");
            zone.activeUsers = response.getInt("active_users");
            zone.viewingUsers = response.getInt("viewing_users");
        } catch (JSONException e) {
            Log.d("ERROR", e.toString());
        }
        messages.remove(bannedMessage);

        Collections.reverse(messages);
        mainActivity.setTitle(zone.viewingUsers + " at " +  zone.name);
        listAdapter.addAll(messages);
        if (listAdapter.getCount() > 0) {
            emptyMessage.setVisibility(View.INVISIBLE);
        } else {
            emptyMessage.setVisibility(View.VISIBLE);
        }
        listAdapter.notifyDataSetChanged();
    }
}
