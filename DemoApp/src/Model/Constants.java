package Model;

import java.util.ArrayList;

public class Constants {
	// list song
	public static ArrayList<SongModel> listSong = new ArrayList<SongModel>();
	//list video
	public static ArrayList<VideoModel> listVideo = new ArrayList<VideoModel>();
	public static final String INTENT_FILTER = "com.mobgame.broadcast";
	public static final String LANG_VI = "-vi";
	public static final String LANG_EN = "-en";
	
	//fragment
	/** TAG **/
	public static final String TAG_FRAGMENT_MAIN = "tag_fragment_main";
	public static final String TAG_FRAGMENT_VIDEO = "tag_fragment_video";
	public static final String TAG_FRAGMENT_SONG = "tag_fragment_song";
	public static final String TAG_FRAGMENT_PLAYVIDEO = "tag_fragment_playvideo";
	public static final String TAG_FRAGMENT_PLAYSONG = "tag_fragment_playsong";
	
	// END TAG
	public static String CLICK_POSITION_SONG = "click_position_song";
	public static String CLICK_POSITION_VIDEO = "click_position_video";
	
	public static int WIDTH_VIDEO = 0;
	public static int HEIGHT_VIDEO = 0;
}
