package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvh.demo.R;

public class CustomViewVideo extends LinearLayout {
	ImageView imageView;
	TextView textTitle;
	TextView textTime;

	public CustomViewVideo(Context context) {
		super(context);
		// su dung layoutInflate de gan giao dien cua minh tao ra
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.listvideo, this, true);
		// lay cac view qua cac id
		imageView = (ImageView) findViewById(R.id.imageViewVideo);
		textTitle = (TextView) findViewById(R.id.name_video);
		textTime = (TextView) findViewById(R.id.textTimeVideo);
	}
	
}
