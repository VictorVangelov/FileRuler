package fileruler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.io.IOUtils;

public class FileUtils {

	public static String encode(String decoded) {
		
		return decoded.replace('\\', '-').replace(':', '_');
	}
	
	public static String decode(String encoded) {
		
		return encoded.replace("-", "\\\\").replace('_', ':');
	}
	
	public static String getFileContent(String filePath) {
		
		String content = new String();
		try(FileInputStream stream = new FileInputStream(new File(filePath))) {
			content = IOUtils.toString(stream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static String getFileName(String filePath){
	    return new File(filePath).getName();
	}
	
	public static void copyFromTo(String from, String to) {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(to), "utf-8"))) {
	   writer.write(getFileContent(from));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static void createFileByContent(String content, String filePath) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(filePath), "utf-8"))) {
	   writer.write(content);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
