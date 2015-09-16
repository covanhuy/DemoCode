package Model;

import android.graphics.Bitmap;

public class VideoModel {

	private String title;
	private String artist;
	private String data;
	private String descriptions;
	private String duration;
	private Bitmap bitmap;
	private String width;
	private String height;
	
	// bit map hiển thị ảnh thumb (ảnh con con ở trong listvideo)
	public VideoModel(String _title, String _artist, String _data, String _descriptions, 
			Bitmap _bitmap, String _duration, String _width, String _height) {
		this.title = _title;
		this.artist = _artist;
		this.data = _data;
		this.descriptions = _descriptions;
		this.bitmap = _bitmap;
		this.duration = _duration;
		this.width = _width;
		this.height = _height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
