package fileruler.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fileruler.dao.MovieDAO;
import fileruler.dao.SongDAO;
import fileruler.model.Movie;
import fileruler.model.Song;

public class Main {
private static String PERSISTENCE_UNIT_NAME = "movies";
	
	private static EntityManagerFactory factory;
	
	
	public static void main(String[] args) {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		MovieDAO movieDAO = new MovieDAO(em);
		SongDAO songDAO = new SongDAO(em);
		List<Movie> movies = new ArrayList<>();
		File file = new File("rsc/movies/");
			
		for (File movieName : file.listFiles()) {
		
			movies.add(MovieUtils.findMovieByNameInIMDB(movieName.getName().substring(0, movieName.getName().indexOf("."))));
		}
		
		Song song[] = {
			new Song("Not Afraid", "Eminem", "Recovery", "Hip Hop", "4:10", "http://upload.wikimedia.org/wikipedia/en/0/00/Eminem_-_Not_Afraid.jpg"),
			new Song("Big poppa", "The Notorious B.I.G", "Ready To Die", "Hip Hop, Rap", "3:29", "http://upload.wikimedia.org/wikipedia/en/thumb/d/d2/BigPoppa.jpg/220px-BigPoppa.jpg")
		};
		
		for (Song song2 : song) {
			
			DownloadPoster.download(song2.getTitle(), song2.getPoster());
			songDAO.add(song2, "sadsadasd/adass");
		}
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Movie").executeUpdate();
		em.getTransaction().commit();
		for (Movie movie : movies) {
			movieDAO.add(movie, "home/dasdsd/movies");
		}
		
		System.out.println("All movies:");
		List<Movie> allMovies = movieDAO.getAllMovies();
		for (Movie movie : allMovies) {
			System.out.println(movie);
		}
		
		System.out.println("/nMovies casting Kevin Hart: ");
		List<Movie> allMoviesByActor = movieDAO.getAllMoviesByActor("Kevin Hart");
		for (Movie movie : allMoviesByActor) {
			System.out.println(movie);
		}
		
		VoiceRecognitionUtils.setMoviesData();
	}
}
