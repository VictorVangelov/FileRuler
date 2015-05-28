package fileruler.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import fileruler.model.Movie;
import fileruler.utils.Splitter;

public class MovieDAO {

    private EntityManager em; // em.close()

    public MovieDAO(EntityManager em) {

        this.em = em;
    }

    public void add(Movie movie, String filePath) {

    	movie.setFilePath(filePath);
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    public List<Movie> getAllMovies() {

        return em.createQuery("SELECT m FROM Movie m", Movie.class)
        		.getResultList();
    }

    public List<Movie> getAllMoviesByActor(String actor) {

        return em.createQuery("SELECT m FROM Movie m WHERE m.actors LIKE '%" + actor + "%'", Movie.class)
                .getResultList();
    }

    public List<Movie> getMoviesByTitle(String title) {

        return em.createQuery("SELECT m FROM Movie m WHERE m.title LIKE '%" + title + "%'", Movie.class)
                .getResultList();
    }
    
    public Set<String> getAllActors() {
    	
    	Set<String> set = new HashSet<String>();
    	List<Movie> movies = getAllMovies();
    	for (Movie movie : movies) {
			set.addAll(Splitter.split(movie.getActors()));
		}
    	return set;
    }
    
    public Set<String> getWritersAndDirectors() {
    	
    	Set<String> set = new HashSet<String>();
    	List<Movie> movies = getAllMovies();
    	for (Movie movie : movies) {
			set.addAll(Splitter.split(movie.getWriters()));
			set.addAll(Splitter.split(movie.getDirectors()));
		}
    	return set;
    }
    
    public Set<String> getAllTitles() {
    	
    	Set<String> set = new HashSet<String>();
    	List<Movie> movies = getAllMovies();
    	for (Movie movie : movies) {
			set.add(movie.getTitle());
		}
    	return set;
    }
} 
