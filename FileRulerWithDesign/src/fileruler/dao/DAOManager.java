package fileruler.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fileruler.model.Movie;

public class DAOManager {
    private static String PERSISTENCE_UNIT_NAME = "movies";

    private static EntityManagerFactory factory;
    private EntityManager em;
    private MovieDAO movieDAO ;

    public void openDBConn() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        movieDAO = new MovieDAO(em);
    }
    
    public void addDataToDB(List<? extends Movie> collection){
        for (Movie movie : collection) {
            movieDAO.add(movie);
        }
    }
    
    public void delete(){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.getTransaction().commit();
    }
    
    public List<? extends Movie> selectAllRecords(){
        return movieDAO.getAllMovies();
    }
    
    public List<? extends Movie> selectSpecificRecords(String whereClause){
       
       return movieDAO.getMoviesByTitle(whereClause);
    }
    
    public void closeDBConn(){
        em.close();
    }
}
