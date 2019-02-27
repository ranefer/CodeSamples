<Most> files needed for Data Binding in Android

Recommended to start from an .xml file to see what files are used and how they interact with the ViewModel
Below is a portion of recycler_view_item.xml and show the relevent info needed for databinding

Note data/variable/type show the class referenced using name=model
and it is referenced @{model.itemTitle} and @{model.itemSubtitle}

```
...
    <data>
        <variable
            name="model"
            type="rising.everest.recyclerview.model.RecyclerItemViewModel"/>
    </data>

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            ...
            android:text="@{model.itemTitle}"
            ...

        <TextView
            ...
            android:text="@{model.itemSubtitle}"
            ...
```

In RecyclerItemViewModel you can see the private variables itemTitle and itemSubtitle
along with the @Bindable getters
@Bindable is an annotation that is used inside a Observable Class such as BaseObservable.
Android studio used this notation in autocompletion of xml's and it signifies a field which is being observed. (When the field is set/updated it triggers an update to the ui if needed)

```
public class RecyclerItemViewModel extends BaseObservable implements Comparable<RecyclerItemViewModel>, RecyclerItem {

	private String itemTitle;

	private String itemSubtitle;

  ...

	@Bindable
	public String getItemTitle() {
		return itemTitle;
	}

	@Bindable
	public String getItemSubtitle() {
		return itemSubtitle;
	}
  ...
```

Be sure to include the following in the gradle
```
    dataBinding {
        enabled true
    }
```
