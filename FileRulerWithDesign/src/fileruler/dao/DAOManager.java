package fileruler.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fileruler.model.Movie;

public class DAOManager<T> implements DAOManagerInterface<T> {
    private static String PERSISTENCE_UNIT_NAME = "movies";

    private static EntityManagerFactory factory;
    private EntityManager em;
    private MovieDAO movieDAO ;

    @Override
    public void openDBConn() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        movieDAO = new MovieDAO(em);
    }
    
    @Override
    public void addDataToDB(List<? extends T> collection){
        for (T movie : collection) {
            movieDAO.add((Movie)movie, "/home/sdsad/movies");
        }
    }
    
    @Override
    public void delete(){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Movie").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Override
    public List<? extends T> selectAllRecords(){
        return (List<? extends T>) movieDAO.getAllMovies();
    }
    
    @Override
    public List<? extends T> selectSpecificRecords(String whereClause){
       
       return (List<? extends T>) movieDAO.getMoviesByTitle(whereClause);
    }
    
    @Override
    public void closeDBConn(){
        em.close();
    }
}
