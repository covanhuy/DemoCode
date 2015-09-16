package fragment;

import Model.Constants;
import adapter.ListSongAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.cvh.demo.R;

public class MusicFragment extends Fragment {
	public static String TAG = MusicFragment.class.getSimpleName();
	private ListSongAdapter adapter;
	private ListView listViewSong;
	private Activity activity;
	public MusicFragment(Activity _activity) {
		this.activity = _activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_list_music, container, false);
		listViewSong = (ListView) rootView.findViewById(R.id.list_song);
		adapter = new ListSongAdapter(activity, R.id.list_song, Constants.listSong);
		listViewSong.setAdapter(adapter);
		//bat xu kien clic vao listview
		listViewSong.setOnItemClickListener(new mListSongListener());
		return rootView;
	}

	class mListSongListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Log.i(TAG, "-->PlaySongActivity");		
			PlayMusicFragment newFragment = new PlayMusicFragment();
			Bundle args = new Bundle();
			args.putInt(Constants.CLICK_POSITION_SONG, position);
			newFragment.setArguments(args);
			Log.i(TAG, "truyen doi so click sang = "+ args);
			FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, newFragment).commit();
			Log.i(TAG, "call fragment play..........");
		}
		
	}
}