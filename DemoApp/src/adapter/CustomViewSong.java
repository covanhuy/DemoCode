package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvh.demo.R;

public class CustomViewSong extends LinearLayout {
	TextView textSong, textArtist, textTime;

	public CustomViewSong(Context context) {
		super(context);
		// set cac text cho ben customview kia
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.listsong, this, true);
		textSong = (TextView) findViewById(R.id.tvName);
		textArtist = (TextView) findViewById(R.id.tvNumber);
		// textTime = (TextView) findViewById(R.id.textTime);
	}
}
