package fileruler.utils;

public class FileUtils {

	public static String encode(String decoded) {
		
		return decoded.replace('\\', '-').replace(':', '_');
	}
	
	public static String decode(String encoded) {
		
		return encoded.replace("-", "\\\\").replace('_', ':');
	}
}
