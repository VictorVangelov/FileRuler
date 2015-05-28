package fileruler.utils;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class MusicFileProperties {

	public static String getFile(String filePath) {
		
		File file = new File(filePath);  
		   
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();     
		return fileSystemView.getSystemTypeDescription(file);  
	}
}
