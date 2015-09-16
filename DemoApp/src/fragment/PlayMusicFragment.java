package fragment;

import service.PlaySongService;
import Model.Constants;
import activity.MediaHelper;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.cvh.demo.R;

public class PlayMusicFragment extends Fragment implements OnSeekBarChangeListener, OnClickListener{
	public static String TAG = PlayMusicFragment.class.getSimpleName();
	
	public MediaPlayer mp = PlaySongService.mediaPlayer;
	private ImageButton imgButtonPlay;
	private ImageButton imgButtonPrevious;
	private ImageButton imgButtonNext;
	private SeekBar seekBar;
	private Thread threadSeekbar;
	private RotateAnimation rotate;
	private ImageView imageView;
	private Intent intent;
	final PlaySongService servicePlaySong = new PlaySongService();
	private Context context = MediaHelper.activity;
	public boolean flagAnimation = true;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragmet_play_music, container ,false);
		//tao view
		imgButtonPrevious = (ImageButton) view.findViewById(R.id.imgPrevious);
		imgButtonPrevious.setBackgroundResource(R.drawable.previous);

		imgButtonPlay = (ImageButton) view.findViewById(R.id.imgPlay);
		imgButtonPlay.setBackgroundResource(R.drawable.pause);

		imgButtonNext = (ImageButton) view.findViewById(R.id.imgNext);
		imgButtonNext.setBackgroundResource(R.drawable.next);
		
		imageView = (ImageView) view.findViewById(R.id.imgCd);
		seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
		//bat su kien các button
		imgButtonNext.setOnClickListener(this);
		imgButtonPlay.setOnClickListener(this);
		imgButtonPrevious.setOnClickListener(this);
		
		//xu ly play music
		Bundle bundle = new Bundle();
		int clickSong = bundle.getInt(Constants.CLICK_POSITION_SONG);
		Log.i(TAG, "get CLICK = " + clickSong);
		String dataPlay = Constants.listSong.get(clickSong).getData();
		
		// bat service
		Intent intent = new Intent(context, service.PlaySongService.class);
		context.startService(intent);
		servicePlaySong.createSong(clickSong);
		servicePlaySong.playSong();
		startSeekBar();

		//start Animation
		rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(15000);
		rotate.setRepeatCount(-1);
		rotate.setInterpolator(new LinearInterpolator());
		startAnimation();
		return view;
	}
	
	public void startSeekBar(){
		seekBar.setProgress(0);
		seekBar.setMax(mp.getDuration());
		threadSeekbar = new Thread() {
			public void run() {
				Log.i(TAG, "start seekbar");
				int currentPosition = 0;
				int total = mp.getDuration();
				while (mp != null && currentPosition < total) {
					try {
						Thread.sleep(1000);
						currentPosition = mp.getCurrentPosition();
					} catch (InterruptedException e) {
						return;
					} catch (Exception e) {
						return;
					}
					seekBar.setProgress(currentPosition);
				}
			}
		};
		threadSeekbar.start();
	}
	
	public void stopAnimation() {
		rotate.cancel();
	}
	
	public void startAnimation(){
		imageView.startAnimation(rotate);
	}
		
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		Log.i(TAG, "changed seekbar...");
		if (fromUser) {
			mp.seekTo(progress);
			seekBar.setProgress(progress);
		}
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Click button
		switch (v.getId()) {
		case R.id.imgNext:
			Log.i(TAG, "next ...");
			servicePlaySong.nextSong();
			startSeekBar();
			break;
		case R.id.imgPlay:
			Log.i(TAG, "play ...");
			if (servicePlaySong.checkPlay() == false) {
				servicePlaySong.playSong();
				imgButtonPlay.setBackgroundResource(R.drawable.pause);
				startAnimation();
				startSeekBar();
			} else {
				servicePlaySong.pauseSong();
				imgButtonPlay.setBackgroundResource(R.drawable.play);
				stopAnimation();
			}
			break;
		case R.id.imgPrevious:
			Log.i(TAG, "previous ...");
			servicePlaySong.previousSong();
			startSeekBar();
			break;

		default:
			break;
		}
	}
	
}
