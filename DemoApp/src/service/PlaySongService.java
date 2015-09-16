package service;

import java.util.ArrayList;
import java.util.Random;

import Model.Constants;
import Model.SongModel;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlaySongService extends Service implements
		MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
		MediaPlayer.OnCompletionListener {

	public static final String TAG = PlaySongService.class.getSimpleName();
	public static MediaPlayer mediaPlayer = new MediaPlayer();
	public static ArrayList<SongModel> tmpListSong = new ArrayList<SongModel>();
//	PlayMusicFragment playMusicFragment = new PlayMusicFragment();
	
	public int currentSong;
	public int totalSong = Constants.listSong.size();
	public boolean flagRandom = true;

	@Override
	public void onCreate() {
		Log.i(TAG, "service onCreate !!!");
		mediaPlayer.setOnCompletionListener(this);

		tmpListSong = Constants.listSong;
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "service onStartCommand !!!");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "service on bind !!!");
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// mediaPlayer.stop();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onstart ");
		super.onStart(intent, startId);

	}

	public void createSong(int click) {
		// play
		mediaPlayer.reset();
		// get Song
		try {
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(Constants.listSong.get(click).getData());
			mediaPlayer.prepare();
			Log.i(TAG, "DATA = " + Constants.listSong.get(click).getData());
			tmpListSong.remove(click);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playSong() {
		Log.i(TAG, "play Song !!!");
		mediaPlayer.start();
//		playMusicFragment.startSeekBar();
		flagRandom = true;
	}

	public void pauseSong() {
		Log.i(TAG, "Pause Song !!!");
		mediaPlayer.pause();
	}

	public void previousSong() {
		int pre;
		if ((currentSong > 0) && (currentSong <= totalSong)) {
			pre = currentSong--;
		} else {
			pre = totalSong;
		}
		Log.i("AAAA", "back = " + pre);
		createSong(pre);
		flagRandom = false;
		playSong();
	}

	public void nextSong() {
		int next;
		if ((currentSong >= 0) && (currentSong < totalSong)) {
			next = currentSong++;
		} else {
			next = 0;
		}
		Log.i("AAAA", "next = " + next);
		createSong(next);
		flagRandom = false;
		playSong();
	}

	public boolean checkPlay() {
		// dang chay tra ve true, k thi tra ve false
		if (mediaPlayer.isPlaying()) {
			return true;
		}
		return false;
	}

	public int getPositionCurrentSong() {
		int idSong = 0;
		if (mediaPlayer.isPlaying()) {
			idSong = mediaPlayer.getCurrentPosition();
			Log.i(TAG, "Dang phat bai id = " + idSong);
			return idSong;
		}
		return idSong;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		if (flagRandom) {
			randomSongs(tmpListSong);
		}

	}

	// ham random nhac k trung nhau
	public void randomSongs(ArrayList<SongModel> arrSong) {
		Log.i("RANDOM ", "size = " + arrSong.size());
		Random ran = new Random();
		int max = arrSong.size();
		currentSong = ran.nextInt(max);
		// play
		try {
			mediaPlayer.reset();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDataSource(arrSong.get(currentSong).getData());
			mediaPlayer.prepare();
			mediaPlayer.start();
			Log.i("RANDOM ", "start " + currentSong);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.i("AAAAA", "random = " + currentSong);
		arrSong.remove(currentSong);

	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.start();
	}

}
