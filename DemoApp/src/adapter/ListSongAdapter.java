package adapter;

import java.util.ArrayList;

import Model.SongModel;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListSongAdapter extends ArrayAdapter<SongModel> {
	ArrayList<SongModel> arrayListSong;
	int resource;
	Context context;

	public ListSongAdapter(Context context, int resourceID,
			ArrayList<SongModel> objects) {
		super(context, resourceID, objects);
		this.context = context;
		resource = resourceID;
		arrayListSong = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View songView = convertView;
		if (songView == null) {
			songView = new CustomViewSong(getContext());
		}
		// lay ve doi tuong video hien tai
		final SongModel songModel = arrayListSong.get(position);
		if (songModel != null) {
			TextView textTitle = ((CustomViewSong) songView).textSong;
			TextView textArtist = ((CustomViewSong) songView).textArtist;
			TextView textTime = ((CustomViewSong) songView).textTime;
			// ImageView videoThumb = ((CustomViewSong) songView).imageView;
			// truyen cac gia tri de hien thi len list
			textTitle.setText(songModel.getTitle());
			textArtist.setText(songModel.getArtist());
//			textTime.setText(songModel.getDuration());
		}
		return songView;
	}

}
