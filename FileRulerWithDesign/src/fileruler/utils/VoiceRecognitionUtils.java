package fileruler.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
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
	
	public static String record() {
        String resultt = new String();
        try {
            URL url;
            
            url = VoiceRecognitionUtils.class.getResource("helloworld.config.xml");
            ConfigurationManager cm = new ConfigurationManager(url);
            Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
            Microphone microphone = (Microphone) cm.lookup("microphone");
            recognizer.allocate();
            if (microphone.startRecording()) {
                System.out.println("Start speaking. ");
                Result result = recognizer.recognize();
                if (result != null) {
                    String resultText = result.getBestFinalResultNoFiller();
                    resultt = resultText;
                }
            }

        } catch (IOException e) {
            System.err.println("Problem when loading HelloWorld: " + e);
            e.printStackTrace();
        } catch (PropertyException e) {
            System.err.println("Problem configuring HelloWorld: " + e);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Problem creating HelloWorld: " + e);
            e.printStackTrace();
        } finally {
            return resultt;
        }
    }
}
