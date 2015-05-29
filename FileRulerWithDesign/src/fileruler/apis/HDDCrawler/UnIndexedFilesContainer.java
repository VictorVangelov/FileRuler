package fileruler.apis.HDDCrawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UnIndexedFilesContainer {
	public static BlockingQueue<String> unIndexedMovies = new LinkedBlockingQueue<String>();
	public static BlockingQueue<String> unIndexedImages = new LinkedBlockingQueue<String>();
	public static BlockingQueue<String> unIndexedAudio = new LinkedBlockingQueue<String>();
	public static BlockingQueue<String> unIndexedText = new LinkedBlockingQueue<String>();

}
