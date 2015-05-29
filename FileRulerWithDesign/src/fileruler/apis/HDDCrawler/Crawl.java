package fileruler.apis.HDDCrawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Crawl {
	// String current = new java.io.File( "." ).getCanonicalPath();
	public void crawl(String startingDir) throws IOException {

		Files.walk(Paths.get(".."))
				.filter(path -> !path.toString().contains(File.separator + "."))
				.map(path -> path.toFile()).filter(file -> file.isFile())
				.map(path -> path.getAbsolutePath())
				.collect(Collectors.toList())
				.forEach(filePath ->FileTypeMatcher.fillUnIndextedFileContainers(filePath));
		;
		/*
		 * .map(pathToFile -> FileTypeMatcher
		 * .fillUnIndextedFileContainers(pathToFile));
		 */
		// .parallel()

		// .forEach(x -> {
		// urls.add(x);
		// System.out.println(x);
		// System.out.println(urls.size());
		// crawl(x);
		// }

		/*
		 * for (String string : urls) { System.out.println(string); //
		 * crawl(Paths.get(string).toString()); }
		 */

	}

	public static void main(String[] args) {
		try {
			new Crawl().crawl("/home/sh0sh0");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
