package fileruler.utils;

import java.util.ArrayList;
import java.util.List;

public class Splitter {

	public static List<String> split(String str) {
		
		String[] split = str.split(", ");
		List<String> result = new ArrayList<String>();
		for (String string : split) {
			result.add(string);
		}
		return result;
	}
} 
