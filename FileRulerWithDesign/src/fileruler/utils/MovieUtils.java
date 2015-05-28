package fileruler.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fileruler.model.Movie;

public class MovieUtils {

	private static final String GOOGLE_RESPONSE_API = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	private static final String IMDB_API_URL = "http://www.omdbapi.com/?i=";
	private static final String IMDB_API_URL_NAME = "http://www.omdbapi.com/?t=";
	
	private static String findIMDBMovieId(String movieName) {
		
		JsonObject googleResponse = JSONUtils.getJSONFromURL(GOOGLE_RESPONSE_API + movieName);
		JsonArray results = googleResponse.get("responseData").getAsJsonObject().get("results").getAsJsonArray();
		for (JsonElement result : results) {
			
			String resultUrl = result.getAsJsonObject().get("url").getAsString();
			if(resultUrl.contains("http://www.imdb.com/title/")) {
				return resultUrl.substring(26, resultUrl.length() - 1);
			}
		}
		return null;	
	}
	
	public static Movie findMovieByName(String movieName) {
		
		movieName = movieName.replace(" ", "%20");
		String movieIMDBId = findIMDBMovieId(movieName);
		JsonObject movieJSON = JSONUtils.getJSONFromURL(IMDB_API_URL + movieIMDBId);
		
		return new Movie(movieJSON.get("Title").getAsString(), movieJSON.get("Year").getAsString(), movieJSON.get("Released").getAsString(),
	    		movieJSON.get("Runtime").getAsString(), movieJSON.get("Genre").getAsString(), movieJSON.get("Director").getAsString(),
	    		movieJSON.get("Writer").getAsString(), movieJSON.get("Actors").getAsString(), movieJSON.get("Plot").getAsString(),
	    		movieJSON.get("Country").getAsString(), movieJSON.get("imdbRating").getAsString(),
	    		movieJSON.get("imdbVotes").getAsString(), "");
	}
	
	public static synchronized Movie findMovieByNameInIMDB(String movieName) {
		
		movieName = movieName.replace(" ", "%20");
		JsonObject movieJSON = JSONUtils.getJSONFromURL(IMDB_API_URL_NAME + movieName);
		System.out.println(movieJSON.get("Title").getAsString());
		DownloadPoster.download(movieJSON.get("Title").getAsString(), movieJSON.get("Poster").getAsString());
		return new Movie(movieJSON.get("Title").getAsString(), movieJSON.get("Year").getAsString(), movieJSON.get("Released").getAsString(),
	    		movieJSON.get("Runtime").getAsString(), movieJSON.get("Genre").getAsString(), movieJSON.get("Director").getAsString(),
	    		movieJSON.get("Writer").getAsString(), movieJSON.get("Actors").getAsString(), movieJSON.get("Plot").getAsString(),
	    		movieJSON.get("Country").getAsString(), movieJSON.get("imdbRating").getAsString(),
	    		movieJSON.get("imdbVotes").getAsString(), "");
	}
}

