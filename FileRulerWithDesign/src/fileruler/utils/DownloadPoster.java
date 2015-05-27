package fileruler.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import fileruler.model.Movie;

public class DownloadPoster {

	public static void download(Movie movie) {
		
		URL url;
		try {
			url = new URL(movie.getPoster());
			InputStream in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream("rsc/posters/" + movie.getTitle() + ".jpg"));

			for ( int i; (i = in.read()) != -1; ) {
			    out.write(i);
			}
			in.close();
			out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
