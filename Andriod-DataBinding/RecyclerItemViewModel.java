package rising.everest.recyclerview.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.Image;

import java.util.Comparator;

public class RecyclerItemViewModel extends BaseObservable implements Comparable<RecyclerItemViewModel>, RecyclerItem {

	private String itemTitle;

	private String itemSubtitle;

	private String category;

	private Image preview;

	private Type type;

	public RecyclerItemViewModel(String title, String subtitle, String category, Image preview) {
		itemSubtitle = subtitle;
		itemTitle = title;
		this.category = category;
		this.preview = preview;
		this.type = Type.ITEM;
	}

	public RecyclerItemViewModel(String category) {
		this.category = category;
		this.type = Type.HEADER;
	}

	@Bindable
	public String getItemTitle() {
		return itemTitle;
	}

	@Bindable
	public String getItemSubtitle() {
		return itemSubtitle;
	}

	@Bindable
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isHeader() {
		return itemTitle == null || itemTitle.isEmpty();
	}

	@Override
	public int compareTo(RecyclerItemViewModel recyclerItemViewModel) {
		if(this.category.equals(recyclerItemViewModel.category))
			return this.itemTitle.compareTo(recyclerItemViewModel.itemTitle);
		return this.category.compareTo(recyclerItemViewModel.category);
	}

	@Override
	public Type getType() {
		return type;
	}
}
