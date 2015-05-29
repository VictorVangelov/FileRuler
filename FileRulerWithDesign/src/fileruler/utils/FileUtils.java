package fileruler.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileUtils {

	public static String encode(String decoded) {
		
		return decoded.replace('\\', '-').replace(':', '_');
	}
	
	public static String decode(String encoded) {
		
		return encoded.replace("-", "\\\\").replace('_', ':');
	}
	
	public static String getFileContent(String filePath) {
		
		String target = new String();
		try(FileInputStream stream = new FileInputStream(new File(filePath))) {
			target = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return target;
	}
	
}
