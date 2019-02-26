package com.app.chatzone.DBComm.GUI;

import org.json.JSONObject;

/**
 * Updates the UI after a thread completes.
 */
public interface UIUpdater {

    /**
     * Updates the UI based on data
     *
     * @param response the result of doInBackground
     *
     * update(..) should only be called in DBCommThread
     */
    public void update(JSONObject response);
}
