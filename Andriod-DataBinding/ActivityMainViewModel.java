package rising.everest.recyclerview.model;

import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import rising.everest.recyclerview.fragment.FabFragment;

public class ActivityMainViewModel extends ViewModel {

	private View.OnClickListener listener;

	public View.OnClickListener getListener() {
		return listener;
	}

	public void setListener(View.OnClickListener listener) {
		this.listener = listener;
	}
}
