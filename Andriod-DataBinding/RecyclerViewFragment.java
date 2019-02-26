package rising.everest.recyclerview;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rising.everest.recyclerview.databinding.RecyclerViewFragmentBinding;
import rising.everest.recyclerview.fragment.FabFragment;
import rising.everest.recyclerview.model.RecyclerItemViewModel;
import rising.everest.recyclerview.model.RecyclerViewViewModel;

public class RecyclerViewFragment extends FabFragment {

	private RecyclerViewViewModel viewModel;

	public RecyclerViewFragment() {
		Bundle args = getArguments();
	}

	public static RecyclerViewFragment newInstance(Bundle args) {
		RecyclerViewFragment fragment = new RecyclerViewFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewModel = ViewModelProviders.of(getActivity()).get(RecyclerViewViewModel.class);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		RecyclerViewFragmentBinding binding = RecyclerViewFragmentBinding.inflate(inflater, container, false);
		binding.setViewModel(viewModel);
		return binding.getRoot();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onClick(View view) {
		List<RecyclerItemViewModel> list = getList2();
		viewModel.getAdapter().update(list);
		//viewModel.setList(list);
	}


	private List<RecyclerItemViewModel> getList2() {
		List<RecyclerItemViewModel> list = new ArrayList<>();
		list.add(new RecyclerItemViewModel("12","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("11","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("10","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("0","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("9","Subtitle","Calendar", null));
		list.add(new RecyclerItemViewModel("8","Subtitle","Calendar", null));
		return list;
	}

}
