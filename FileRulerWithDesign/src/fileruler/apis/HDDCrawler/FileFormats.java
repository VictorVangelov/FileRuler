package fileruler.apis.HDDCrawler;

import java.util.ArrayList;
import java.util.Arrays;

public class FileFormats {
	protected static ArrayList<String> videoFormats = new ArrayList<String>(
			Arrays.asList("avi", "mkv", "flv", "mng", "wmv", "mp4", "mpeg",
					"3gp"));
	protected static ArrayList<String> audioFormats = new ArrayList<String>(
			Arrays.asList("mp3", "wma", "wav", "raw", "aiff", "webm", "m4a"));

	protected static ArrayList<String> textFormats = new ArrayList<String>(
			Arrays.asList("txt", "docx", "doc", "wpd"));

	protected static ArrayList<String> imageFormats = new ArrayList<String>(
			Arrays.asList("jpeg", "jpg", "bmp", "png", "hdr"));
}
