package rising.everest.recyclerview.model;

import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rising.everest.recyclerview.RecyclerViewAdapter;

public class RecyclerViewViewModel extends ViewModel {

	private RecyclerViewAdapter adapter;

	public RecyclerViewViewModel() {
		this.adapter = new RecyclerViewAdapter();
		this.adapter.update(getNewList());
	}
	public RecyclerViewAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(RecyclerViewAdapter adapter) {
		this.adapter = adapter;
	}

	@BindingAdapter("adapter")
	public static void setAdapter(RecyclerView view, RecyclerViewAdapter adapter) {
		view.setAdapter(adapter);
	}

	private List<RecyclerItemViewModel> getNewList() {
		List<RecyclerItemViewModel> list = new ArrayList<>();
		list.add(new RecyclerItemViewModel("Big Tonys","5 stars","Groceries", null));
		list.add(new RecyclerItemViewModel("Pizza","Subtitle","Groceries", null));
		list.add(new RecyclerItemViewModel("Yogurt","Subtitle","Groceries", null));
		list.add(new RecyclerItemViewModel("Granola","Subtitle","Groceries", null));
		list.add(new RecyclerItemViewModel("Chopin","Subtitle","Classical Music", null));
		list.add(new RecyclerItemViewModel("Motzart","Subtitle","Classical Music", null));
		list.add(new RecyclerItemViewModel("Debussy","Subtitle","Classical Music", null));
		list.add(new RecyclerItemViewModel("Yiruma","Subtitle","Classical Music", null));
		list.add(new RecyclerItemViewModel("Thanksgiving","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("12","Subtitle","Calendar", null));
		return list;
	}

}
