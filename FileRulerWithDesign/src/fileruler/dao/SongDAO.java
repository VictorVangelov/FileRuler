package fileruler.dao;

import javax.persistence.EntityManager;

import fileruler.model.Song;

public class SongDAO {

	private EntityManager em;
	public SongDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void add(Song song, String filePath) {
		
		song.setFilePath(filePath);
		em.getTransaction().begin();
		em.persist(song);
		em.getTransaction().commit();
	}
	
	public Song findSongByTitle(String title) {
		
		return em.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
				.getSingleResult();
	}
	
}
