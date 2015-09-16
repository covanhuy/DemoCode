package adapter;

import java.util.ArrayList;

import Model.MenuDrawerItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cvh.demo.R;

public class MenuDrawerItemAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MenuDrawerItem> menuDrawerItems;

	public MenuDrawerItemAdapter(Context context,
			ArrayList<MenuDrawerItem> navDrawerItems) {
		this.context = context;
		this.menuDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menuDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.drawer_list_items, null);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

		imgIcon.setImageResource(menuDrawerItems.get(position).getIcon());
		txtTitle.setText(menuDrawerItems.get(position).getTitle());

		// displaying count
		// check whether it set visible or not
		if (menuDrawerItems.get(position).getIsCounterVisible()) {
			txtCount.setText(menuDrawerItems.get(position).getCount());
		} else {
			// hide the counter view
			txtCount.setVisibility(View.GONE);
		}

		return convertView;
	}

}
