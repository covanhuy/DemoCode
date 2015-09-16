package fragment;

import Model.Constants;
import activity.MainActivity;
import activity.MediaHelper;
import activity.PlayVideoActivity;
import adapter.ListVideoAdapter;
import android.R.integer;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cvh.demo.R;

public class VideoFragment extends Fragment{
	public static String TAG = VideoFragment.class.getSimpleName();
	private ListVideoAdapter adapter;
	private ListView listViewVideo;
	private Activity activity;

	public VideoFragment(Activity _activity) {
		this.activity = _activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_list_video,
				container, false);
		listViewVideo = (ListView) rootView.findViewById(R.id.list_video);
		adapter = new ListVideoAdapter(activity, R.id.list_video,
				Constants.listVideo);
		listViewVideo.setAdapter(adapter);
		listViewVideo.setOnItemClickListener(new mListVideoListener());
		
		
		return rootView;
	}
	class mListVideoListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Log.i(TAG, "-->PlayVideoActivity");
			//TODO dùng activity cho cái này không thể dùng fragment
//			Bundle args = new Bundle();
//			args.putInt(Constants.CLICK_POSITION_VIDEO, position);
			
			Constants.WIDTH_VIDEO = Integer.parseInt(Constants.listVideo.get(position).getWidth());
			Constants.HEIGHT_VIDEO = Integer.parseInt(Constants.listVideo.get(position).getHeight());
			Log.i(TAG, "click position video = "+ position);
			Log.i(TAG, "with x height = " + Constants.WIDTH_VIDEO + " x " + Constants.HEIGHT_VIDEO);
			
			Intent intent = new Intent(MediaHelper.activity, PlayVideoActivity.class);
			intent.putExtra(Constants.CLICK_POSITION_VIDEO, position);
			startActivity(intent);
			Log.i(TAG, "truyen doi so click video sang = "+ position);
		}
		
	}
}
