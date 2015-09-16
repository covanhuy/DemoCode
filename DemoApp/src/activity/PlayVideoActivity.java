package activity;

import java.io.IOException;

import service.PlaySongService;
import Model.Constants;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

import com.cvh.demo.R;

public class PlayVideoActivity extends Activity implements
		SurfaceHolder.Callback, OnPreparedListener, OnVideoSizeChangedListener,
		OnErrorListener, OnCompletionListener, OnInfoListener,
		OnSeekCompleteListener {

	public static String TAG = PlayVideoActivity.class.getSimpleName();

	private SensorManager mSensorManager;
	private PowerManager mPowerManager;
	private WindowManager mWindowManager;
	private WakeLock mWakeLock;
	private Sensor mLight;
	private Intent intent;
	private MediaController mediaController;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	public static MediaPlayer mediaPlayer;
	int videoHeight =  Constants.HEIGHT_VIDEO , videoWidth =  Constants.WIDTH_VIDEO;
	Display currentDisplay;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// hiển thị full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_play_video);
		Log.i(TAG, "relution = " + Constants.WIDTH_VIDEO + " x "+ Constants.HEIGHT_VIDEO);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.setOnErrorListener(this);
		mediaPlayer.setOnInfoListener(this);
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setOnSeekCompleteListener(this);
		mediaPlayer.setOnVideoSizeChangedListener(this);

		mediaController = new MediaController(this);
		mediaController.setAnchorView(surfaceView);
		mediaController.setMediaPlayer(new SerenityMediaPlayerControl());
		
		// lay url tu ben listActivity sang de chay video
		Bundle data = new Bundle() ;
		data = getIntent().getExtras();
		int clickVideo = data.getInt(Constants.CLICK_POSITION_VIDEO);
		Log.i(TAG, "----------------------------------get -----------------------");
		Log.i(TAG, "get Click = " + clickVideo);
		String url = Constants.listVideo.get(clickVideo).getData();
		try {
			mediaPlayer.setDataSource(url);
			Log.i(TAG, "Get Url = " + url);
		} catch (Exception e) {
			Log.e("Loi :", e.getMessage());
			finish();
		}
		
		// sensor
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// PowerManager
		mPowerManager = (PowerManager) getSystemService(POWER_SERVICE);
		// Get an instance of the WindowManager
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay();
		
		// khong cho man hinh tat
		mWakeLock = mPowerManager.newWakeLock(
				PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());

	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_video, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// ----------------------------------------------
	public class SerenityMediaPlayerControl implements MediaPlayerControl {

		@Override
		public void start() {
			mediaPlayer.start();
			mWakeLock.acquire();
		}

		@Override
		public void pause() {
			mediaPlayer.pause();
			mWakeLock.release();
		}

		@Override
		public int getDuration() {
			return mediaPlayer.getDuration();
		}

		@Override
		public int getCurrentPosition() {
			return mediaPlayer.getCurrentPosition();
		}

		@Override
		public void seekTo(int pos) {
			mediaPlayer.seekTo(pos);
		}

		@Override
		public boolean isPlaying() {
			return mediaPlayer.isPlaying();
		}

		@Override
		public int getBufferPercentage() {
			return 0;
		}

		@Override
		public boolean canPause() {
			return true;
		}

		@Override
		public boolean canSeekBackward() {
			return true;
		}

		@Override
		public boolean canSeekForward() {
			return true;
		}

		public int getAudioSessionId() {
			return 0;
		}

	}

	// ----------------------------------------------

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onComplete called");
		finish();
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onPrepared Called........");
		videoWidth = Constants.WIDTH_VIDEO;
		videoHeight = Constants.HEIGHT_VIDEO;//with = 630 x height 360
		
		currentDisplay = getWindowManager().getDefaultDisplay();
		Log.i(TAG, "current display = " + currentDisplay);//with = 1200 x height 1824
		
		//xu ly cho man hinh doc 
		if(videoWidth > currentDisplay.getWidth() || videoHeight > currentDisplay.getHeight()){
			//neu do rong cua video lon hon man hinh
			Log.i(TAG, "ti le man hinh nho hon ");
			float heightRatio = (float)videoHeight/(float)currentDisplay.getHeight();
			float widthRatio = (float)videoWidth/(float)currentDisplay.getWidth();
			int ratio = Math.min((int)Math.ceil(widthRatio), (int)Math.ceil(heightRatio));
			//ti le lon hon 1
			videoWidth = Constants.WIDTH_VIDEO/ratio;
			videoHeight = Constants.HEIGHT_VIDEO/ratio;
			
			surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
			Log.i(TAG, "show with display = " + videoWidth + " x " + videoHeight);
		}else{
			//neu do rong cua video nho hon man hinh
			Log.i(TAG, "ti le man hinh lon hon ");
			float heightRatio = (float)currentDisplay.getHeight()/(float)videoHeight;
			float widthRatio =(float)currentDisplay.getWidth()/(float)videoWidth;
			int ratio = Math.min((int)Math.ceil(widthRatio), (int)Math.ceil(heightRatio));
			//ti le lon hon 1
			videoWidth = Constants.WIDTH_VIDEO*ratio;
			videoHeight = Constants.HEIGHT_VIDEO*ratio;
			
			surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
			Log.i(TAG, "show with display = " + videoWidth + " x " + videoHeight);
		}
		
		// mp.start();
		//giu cho man hinh k ngu khi dang chay video
		if (mp.isPlaying()) {
			mWakeLock.acquire();
		} else {
			// mediaPlayer.pause();
			// mediaPlayer.stop();
		}
	}
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
			// chuẩn bị video
			mediaPlayer.prepare();
			
			mediaPlayer.setDisplay(holder);
			
			// check service nhac co bat khong neu bat thi stop
			PlaySongService serviceSong = new PlaySongService();
			if (serviceSong.mediaPlayer.isPlaying()) {
				serviceSong.pauseSong();
			}
			//
			mediaPlayer.start();

		} catch (IllegalStateException e) {
			Log.e("LOGTAG", e.getMessage());
			finish();
		} catch (IOException e) {
			Log.e("LOGTAG", e.getMessage());
			finish();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.i(TAG, "suface surfaceChanged called");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method
		Log.i(TAG, "suface destroyed called");
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int whatInfo, int extra) {
		// TODO Auto-generated method stub
		if(whatInfo == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING){
			Log.i(TAG, "Media info, media info bad interleaving" + extra);
		}else if(whatInfo == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE){
			Log.i(TAG, "Media info, media info not seekable" + extra);
		}else if(whatInfo == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING){
			Log.i(TAG, "MediaInfor, Media Infor VIdeo Track Lagging " + extra);
		}else if(whatInfo == MediaPlayer.MEDIA_INFO_METADATA_UPDATE){
			Log.i(TAG, "MediaInfor, Media Infor Metadata Update " + extra);
		}
		return false;
	}

	@Override
	public boolean onError(MediaPlayer mp, int whatError, int extra) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onError called");
		
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(mediaController.isShowing()){
			mediaController.hide();
		}else {
			mediaController.show();
		}
		
		return super.onTouchEvent(event);
	}
	
}
