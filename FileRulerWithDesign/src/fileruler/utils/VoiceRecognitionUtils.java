package fileruler.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fileruler.dao.MovieDAO;

public class VoiceRecognitionUtils {

	private static String PERSISTENCE_UNIT_NAME = "movies";
	private static EntityManagerFactory factory;
	public static void setMoviesData() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		MovieDAO movieDao = new MovieDAO(em);
		Set<String> movieTitles = movieDao.getAllTitles();
		writeToFile("MovieTitles.txt", movieTitles);
		Set<String> movieActors = movieDao.getAllActors();
		writeToFile("MovieActors.txt", movieActors);
		Set<String> directorsWriters = movieDao.getWritersAndDirectors();
		writeToFile("DirectorsWritersData.txt", directorsWriters);
		Set<String> genres = new HashSet<String>();
		for (GenreEnum string : GenreEnum.values()) {
			genres.add(string.toString());
		}
		writeToFile("Genres.txt", genres);
		
	}
	
	private static void writeToFile(String filePath, Collection<String> data) {
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rsc/VoiceRecognitionData/" + filePath, false)))) {
		    
			for (String string : data) {
				out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
