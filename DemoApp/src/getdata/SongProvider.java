package getdata;

import java.util.ArrayList;

import Model.Constants;
import Model.SongModel;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;



public class SongProvider extends Activity {
	public static String TAG = SongProvider.class.getSimpleName();
	private ContentResolver contentResolver;
	private int stt = 0;
	
	public SongProvider(Activity fragmetAct) {
		contentResolver = fragmetAct.getContentResolver();
	}

	public ArrayList<SongModel> getAllSong() {

		Boolean isSDPresent = android.os.Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);
		Uri uri = null;
		if (isSDPresent) {
			// the da san sang
			uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		} else {
			// the chua san sang
			uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
		}
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
			return Model.Constants.listSong;
		}
		if (!cursor.moveToFirst()) {
			return Model.Constants.listSong;
		}
		// lay cac cot du lieu duoc luu tru cua song
		int title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
		int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
		int abuml = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
		//danh dau			
		int bookmark = cursor.getColumnIndex(MediaStore.Audio.Media.BOOKMARK);
		//duong dan file
		int data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
		int duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
		//ten tac gia
		int composer =  cursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER);
		int artist_key = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_KEY);
		int is_music = cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC);
		do {
			String _title = cursor.getString(title);
			String _artist = cursor.getString(artist);
			String _abuml = cursor.getString(abuml);
			String _bookmark = cursor.getString(bookmark);
			String _data = cursor.getString(data);

			String _duration = cursor.getString(duration);
			double duration_tmp = (double) (Long.parseLong(_duration)*0.001/60);
			String _time = String.valueOf((double)Math.round(duration_tmp*100)/100 );
			
			String _artist_key = cursor.getString(artist_key);
			String _composer = cursor.getString(composer);
			String _is_music = cursor.getString(is_music);
			
			/*Log.i("AAAA", "--------------------------------------------");
			Log.i("AAAA", "_title = "+ _title);
			Log.i("AAAA", "_artist = "+ _artist);
			Log.i("AAAA", "_abuml = "+ _abuml);
			Log.i("AAAA", "_bookmark = "+ _bookmark);
			Log.i("AAAA", "_data = "+ _data);
			Log.i("AAAA", "stringtimeSong = "+ stringtimeSong);
			Log.i("AAAA", "artist_key = "+ artist_key);
			Log.i("AAAA", "_composer = "+ _composer);
			Log.i("AAAA", "_is_music = "+ _is_music);*/
			// --------------------------------------------------------
			if(_is_music.equals("1")){
				SongModel item = new SongModel(stt, _title, _artist, _abuml, _bookmark, _data, _time, _composer);
				Constants.listSong.add(item);
//				Log.i(TAG, "stt = " + stt);
				stt++;
			}
			
		} while (cursor.moveToNext());
		cursor.close();
		return Constants.listSong;
	}
}
