package fileruler.apis.HDDCrawler;

public class FileTypeMatcher {

	protected static void fillUnIndextedFileContainers(String pathToFile) {
		// System.out.println(pathToFile.substring(pathToFile.lastIndexOf('.')+1));
		String fileExtension = pathToFile.substring(pathToFile.lastIndexOf('.') + 1);
		//System.out.println(fileExtension);
		if (fileExtension.equals(null)) {
		} else if (isVideoFormat(fileExtension)) {
			UnIndexedFilesContainer.unIndexedMovies.add(pathToFile);
			System.out.println("add in Video");
		} else if (isTextFormat(fileExtension)) {
			System.out.println("add in text");
			UnIndexedFilesContainer.unIndexedText.add(pathToFile);
		} else if (isImageFormat(fileExtension)) {
			System.out.println("add in IMG");
			UnIndexedFilesContainer.unIndexedImages.add(pathToFile);
		} else if (isAudioFormat(fileExtension)) {
			System.out.println("add in music");
			UnIndexedFilesContainer.unIndexedAudio.add(pathToFile);
		}
	}

	private static boolean isVideoFormat(String fileExtension) {

		return FileFormats.videoFormats.contains(fileExtension);
	}

	private static boolean isTextFormat(String fileExtension) {

		return FileFormats.textFormats.contains(fileExtension);
	}

	private static boolean isAudioFormat(String fileExtension) {
		return FileFormats.audioFormats.contains(fileExtension);
	}

	private static boolean isImageFormat(String fileExtension) {
		return FileFormats.imageFormats.contains(fileExtension);

	}

}
