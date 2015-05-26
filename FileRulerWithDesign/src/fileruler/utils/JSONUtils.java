package fileruler.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtils {

	public static JsonObject getJSONFromURL(String urlString) {
		
		URL url;
		try {
			url = new URL(urlString);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
		    request.connect();
		    JsonParser jsonParser = new JsonParser();
		    JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) request.getContent())); 
		    JsonObject jsonObject = jsonElement.getAsJsonObject(); 
		    return jsonObject;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
