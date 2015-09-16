package adapter;

import java.util.ArrayList;

import Model.VideoModel;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListVideoAdapter extends ArrayAdapter<VideoModel> {
	ArrayList<VideoModel> arrayListVieo;
	int resource;
	Context context;

	public ListVideoAdapter(Context context, int textViewResourceID,
			ArrayList<VideoModel> object) {
		super(context, textViewResourceID, object);
		this.context = context;
		resource = textViewResourceID;
		arrayListVieo = object;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View videoView = convertView;
		if (videoView == null) {
			videoView = new CustomViewVideo(getContext());
		}
		// lay ve doi tuong video hien tai
		final VideoModel vItem = arrayListVieo.get(position);
		if (vItem != null) {
			ImageView videoThumb = ((CustomViewVideo) videoView).imageView;
			TextView nameVideo = ((CustomViewVideo) videoView).textTitle;
			TextView timeVideo = ((CustomViewVideo) videoView).textTime;
			
			nameVideo.setText(vItem.getTitle());
			timeVideo.setText(vItem.getDuration());
			videoThumb.setImageBitmap(vItem.getBitmap());
		}
		return videoView;

	}

}
