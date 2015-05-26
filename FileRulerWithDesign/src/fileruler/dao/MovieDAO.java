package fileruler.dao;

import java.util.List;

import javax.persistence.EntityManager;

import fileruler.model.Movie;

public class MovieDAO {

    private EntityManager em; // em.close()

    public MovieDAO(EntityManager em) {

        this.em = em;
    }

    public void add(Movie movie) {

        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
    }

    public List<Movie> getAllMovies() {

        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public List<Movie> getAllMoviesByActor(String actor) {

        return em.createQuery("SELECT m FROM Movie m WHERE m.actors LIKE '%" + actor + "%'", Movie.class)
                .getResultList();
    }

    public List<Movie> getMoviesByTitle(String title) {

        return em.createQuery("SELECT m FROM Movie m WHERE m.title LIKE '%" + title + "%'", Movie.class)
                .getResultList();
    }
}
