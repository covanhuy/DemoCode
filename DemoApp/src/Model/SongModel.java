package Model;

public class SongModel {
	public int id;
	public String title;
	public String artist;
	public String abuml;
	public String bookmark; // danh dau file
	public String data; // dia chi luu
	public String duration; // thoi gian cua file
	public String composer;

	public SongModel(int _id, String _title, String _artist, String _abuml,
			String _bookmark, String _data, String _duration, String _composer) {
		super();
		this.id = _id;
		this.title = _title;
		this.artist = _artist;
		this.abuml = _abuml;
		this.bookmark = _bookmark;
		this.data = _data;
		this.duration = _duration;
		this.composer = _composer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbuml() {
		return abuml;
	}

	public void setAbuml(String abuml) {
		this.abuml = abuml;
	}

	public String getBookmark() {
		return bookmark;
	}

	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
		return duration;
	}

	public void setDescriptions(String descriptions) {
		this.duration = descriptions;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
