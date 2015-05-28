package fileruler.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sound.sampled.AudioFileFormat;

import fileruler.dao.MovieDAO;
import fileruler.model.Movie;
import fileruler.view.BaseController;

public class Main {
    private static String PERSISTENCE_UNIT_NAME = "movies";

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        System.out.println(new File("FileRulerWithDesign/rsc/posters/Focus.jpg").getPath().toString());
        // factory =
        // Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        // EntityManager em = factory.createEntityManager();
        // MovieDAO movieDAO = new MovieDAO(em);
        // List<Movie> movies = new ArrayList<>();
        // String[] movieNames = {
        // "Game of Thrones - 5x03 - High Sparrow",
        // "Focus (2015)",
        // "Game of Thrones - 5x05 - Kill the Boy",
        // "The Wedding Ringer",
        // "Get Hard"
        // };
        //
        // for (String movieName : movieNames) {
        //
        // movies.add(MovieUtils.findMovieByName(movieName));
        // }
        // em.getTransaction().begin();
        // em.createQuery("DELETE FROM Movie").executeUpdate();
        // em.getTransaction().commit();
        // for (Movie movie : movies) {
        // movieDAO.add(movie);
        // }
        //
        // System.out.println("All movies:");
        // List<Movie> allMovies = movieDAO.getAllMovies();
        // for (Movie movie : allMovies) {
        // DownloadPoster.download(movie);
        // System.out.println(movie);
        // }
        //
        // System.out.println("/nMovies casting Kevin Hart: ");
        // List<Movie> allMoviesByActor =
        // movieDAO.getAllMoviesByActor("Kevin Hart");
        // for (Movie movie : allMoviesByActor) {
        // System.out.println(movie);
        // }
        // em.close();
        //

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        MovieDAO movieDAO = new MovieDAO(em);
        List<Movie> movies = new ArrayList<>();
        String[] movieNames = { "Game of Thrones", "Focus", "Game of Thrones", "The Wedding Ringer", "Get Hard" };
        int i = 0;
        for (String movieName : movieNames) {
            movies.add(MovieUtils.findMovieByNameInIMDB(movieName));
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
            DownloadPoster.download(movie);
            System.out.println(movie);
        }

        System.out.println("/nMovies casting Kevin Hart: ");
        List<Movie> allMoviesByActor = movieDAO.getAllMoviesByActor("Kevin Hart");
        for (Movie movie : allMoviesByActor) {
            System.out.println(movie);
        }

        System.out.println("All actors:");
        Set<String> actors = movieDAO.getAllActors();
        for (String string : actors) {
            System.out.println(string);
        }

        System.out.println("All directorsWriters:");
        Set<String> directorsWriters = movieDAO.getWritersAndDirectors();
        for (String string : directorsWriters) {
            System.out.println(string);
        }

        System.out.println("All Titles:");
        Set<String> titles = movieDAO.getAllTitles();
        for (String string : titles) {
            System.out.println(string);
        }

    }
}
