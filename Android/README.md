Uses the strategy pattern for all AsyncTasks backend calls to reduce code & repeat in other files

To make a new request implement a new DBComm and UIUpdater and call
```
new DBCommThread(ExampleDBComm, ExampleUIUpdater).execute()
```

Example implementations of DBComm and UIUpdater are provided


```
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
```

DBComm & UIUpdater

```
public interface DBComm {
    public JSONObject result();
}

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
```
