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
			new Song("Lose Yourself", "Eminem", "Recovery", "Hip Hop", "5:26", "http://upload.wikimedia.org/wikipedia/en/thumb/d/d6/Lose_Yourself.jpg/220px-Lose_Yourself.jpg", "rsc/songs/Eminem - Lose Yourself.mp3"),
			new Song("The Pretender", "Foo Fighters", " Echoes, Silence, Patience & Grace", "Post-grunge, Hard Rock", "4:29", "http://upload.wikimedia.org/wikipedia/en/thumb/8/8b/The_Pretender_FF_New_Single.jpg/220px-The_Pretender_FF_New_Single.jpg", "rsc/songs/Foo Fighters - The Pretender.mp3"),
			new Song("Fear Of The Dark", "Iron Maiden","A Real Live One", "Heavy metal", "7:22", "http://upload.wikimedia.org/wikipedia/en/thumb/2/29/MaidenFear93.jpeg/220px-MaidenFear93.jpeg", "rsc/songs/Iron Maden - Fear Of The Dark.mp3"),
			new Song("Melody", "Oliver Heldens", "Melody", "House", "5:04", "http://i1.sndcdn.com/artworks-000114586768-v28spd-t500x500.jpg", "rsc/songs/Oliver Heldens - Melody.mp3")
		};
		
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Movie").executeUpdate();
		em.getTransaction().commit();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Song").executeUpdate();
		em.getTransaction().commit();
		
		for (Song song2 : song) {
			
			songDAO.add(song2);
		}
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
		
		System.out.println("Song by title:");
		System.out.println(songDAO.findSongByTitle("Fear Of The Dark"));
		
		System.out.println("All songs:");
		List<Song> songs = songDAO.getAllSongs();
		for (Song song2 : songs) {
			System.out.println(song2);
		}
		VoiceRecognitionUtils.setMoviesData();
		
		System.out.println(FileUtils.encode("C:\\ProgramFiles\\SomeDocs\\myInfo.txt"));
		System.out.println(FileUtils.decode(FileUtils.encode("C:\\ProgramFiles\\SomeDocs\\myInfo.txt")));
	}
}
