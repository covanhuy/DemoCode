package activity;

import getdata.SongProvider;
import getdata.VideoProvider;
import Model.Constants;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class MediaHelper {
	private static String TAG = MediaHelper.class.getSimpleName();
	TextView txtTitle;
	ListView lvMenu;
	public View rootView;

//	private SensorEventListener sensor;
	public static Activity activity;

	@SuppressWarnings("static-access")
	public MediaHelper(Activity _fragmentActivity) {
		this.activity = _fragmentActivity;
		initView();
	}

	public void initView() {
		// song
		SongProvider songProvider = new SongProvider(activity);
		Constants.listSong = songProvider.getAllSong();
		Log.i("AAA", "List song size = " + Constants.listSong.size());
		// video
		try {
			VideoProvider videoP = new VideoProvider(activity);
			Constants.listVideo = videoP.getAllVideos();
			Log.i("AAA", "List video size = " + Constants.listVideo.size());
		} catch (Exception e) {
			Log.e("TAG", e.toString());
		}
	}

	public void onCreate() {
		Log.i(TAG, "onCreate");
	}

	public void start() {
		Log.i(TAG, "onStart");
	}

	public void onResume() {
		Log.i(TAG, "onResume");
	}

	public void onPause() {
		Log.i(TAG, "onPause");
	}

	public void onStop() {
		Log.i(TAG, "onStop");
	}

	public void onDestroy() {
		Log.i(TAG, "onDestroy");
	}
}
