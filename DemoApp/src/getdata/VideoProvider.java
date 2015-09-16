package getdata;

import java.util.ArrayList;

import Model.Constants;
import Model.VideoModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.util.Log;


public class VideoProvider extends Activity {
	public static String TAG = "VIDEO PROVIDER ::";
	private ContentResolver contentResolver;

	public VideoProvider(Activity act) {
		contentResolver = act.getContentResolver();
	}

	// video
	@SuppressLint("InlinedApi")
	public ArrayList<Model.VideoModel> getAllVideos() {
		// kiem tra su san sang cho viec doc ghi du lieu o the sdcard
		Boolean isSDPresent = android.os.Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);
		Uri uri = null;
		if (isSDPresent) {// check memory card
			uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
		} else {
			uri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
		}
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
			return Constants.listVideo;
		}
		if (!cursor.moveToFirst()) {
			return Constants.listVideo;
		}
		
		// lay cac cot du lieu duoc luu tru cua video
		int title = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
		int artist = cursor.getColumnIndex(MediaStore.Video.Media.ARTIST);
		int data = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
		int descr = cursor.getColumnIndex(MediaStore.Video.Media.DESCRIPTION);
		int id = cursor.getColumnIndex(MediaStore.Video.Media._ID);
		int duration = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);
		int resolution = cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION);
		int with = cursor.getColumnIndex(MediaStore.Video.Media.WIDTH);
		int height = cursor.getColumnIndex(MediaStore.Video.Media.HEIGHT);

		do {
			String _title = cursor.getString(title);
			String _artist = cursor.getString(artist);
			String _data = cursor.getString(data);
			String _descriptions = cursor.getString(descr);
			String _id = cursor.getString(id);
			
			// lay thumbImage
			Bitmap bitmap = null;
			try {
				int id2 = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
				String[] proj = { BaseColumns._ID };
				Cursor c = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj,null, null, null);
				if (c.moveToFirst()) {
					do {
						bitmap = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id2, MediaStore.Video.Thumbnails.MICRO_KIND, null);
					} while (c.moveToNext());
				}
				c.close();
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}
			String _duration = cursor.getString(duration);
			String _resolution = cursor.getString(resolution);
			String _width = cursor.getString(with);
			String _height = cursor.getString(height);
			//time video
			double duration_tmp = (double) (Long.parseLong(_duration)*0.001/60);
			String _time = String.valueOf((double)Math.round(duration_tmp*100)/100 );
			//**********************result data video*************************
			/*Log.i("", "-----------------------------------------------");
			Log.i("", "_title = " + _title);
			Log.i(TAG, "_artist = " + _artist);
			Log.i(TAG, "_data = " + _data);
			Log.i(TAG, "_descriptions = " + _descriptions);
			Log.i(TAG, "_id = " + _id);
			Log.i(TAG, "path = " + path);
			Log.i(TAG, "_resolution = " + _resolution);
			Log.i(TAG, "Time = " + _time);
			Log.i(TAG, "_width = " + _width);
			Log.i(TAG, "_height = " + _height);
			Log.i("", "-----------------------------------------------");*/
			// --------------------------------------------------------
			if(_resolution != null){
				VideoModel item = new VideoModel(_title, _artist, _data, _descriptions, bitmap, _time, _width,_height);
				Constants.listVideo.add(item);
			}

		} while (cursor.moveToNext());
		cursor.close();
		return Constants.listVideo;
	}

}
