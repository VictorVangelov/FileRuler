package fileruler.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fileruler.dao.MovieDAO;
import fileruler.model.Movie;

public class Main {
private static String PERSISTENCE_UNIT_NAME = "movies";
	
	private static EntityManagerFactory factory;
	
	
	public static void main(String[] args) {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		MovieDAO movieDAO = new MovieDAO(em);
		List<Movie> movies = new ArrayList<>();
		String[] movieNames = {
			"Game of Thrones - 5x03 - High Sparrow",
			"Focus (2015)",
			"Game of Thrones - 5x05 - Kill the Boy",
			"The Wedding Ringer",
			"Get Hard"
		};
			
		for (String movieName : movieNames) {
		
			movies.add(MovieUtils.findMovieByName(movieName));
		}
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Movie").executeUpdate();
		em.getTransaction().commit();
		for (Movie movie : movies) {
			movieDAO.add(movie);
		}
		
		System.out.println("All movies:");
		List<Movie> allMovies = movieDAO.getAllMovies();
		for (Movie movie : allMovies) {
			DownloadPoster.download(movie);
			System.out.println(movie);
		}
		
		System.out.println("/nMovies casting Kevin Hart: ");
		List<Movie> allMoviesByActor = movieDAO.getAllMoviesByActor("Kevin Hart");
		for (Movie movie : allMoviesByActor) {
			System.out.println(movie);
		}
		em.close();
	}
}
