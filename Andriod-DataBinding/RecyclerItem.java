package rising.everest.recyclerview.model;

import rising.everest.recyclerview.R;

public interface RecyclerItem {
	public static enum Type {
		HEADER(R.layout.recycler_view_header),
		ITEM(R.layout.recycler_view_item);

		private int viewType;

		Type(int viewType) {
			this.viewType = viewType;
		}

		public int getViewType() {
			return viewType;
		}
	};

	public Type getType();

}
