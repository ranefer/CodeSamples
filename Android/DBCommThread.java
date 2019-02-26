package com.app.chatzone.DBComm.threads;

import android.os.AsyncTask;

import com.app.chatzone.DBComm.GUI.UIUpdater;
import com.app.chatzone.DBComm.query.DBComm;

import org.json.JSONObject;

/**
 * A generic Thread that can handle all server interaction
 */
public class DBCommThread extends AsyncTask<Object, Object, JSONObject> {

    DBComm comm;
    UIUpdater gui;
    public DBCommThread(DBComm comm, UIUpdater gui) {
        this.comm = comm;
        this.gui = gui;
    }

    @Override
    protected JSONObject doInBackground(Object... params) {
        return comm.result();
    }

    @Override
    protected void onPostExecute(JSONObject o) {
        super.onPostExecute(o);
        if(gui != null) {
            gui.update(o);
        }
    }
}
