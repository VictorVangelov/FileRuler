package fileruler.dao;

import java.util.List;

import javax.persistence.EntityManager;

import fileruler.model.Song;
import fileruler.utils.DownloadPoster;

public class SongDAO {

	private EntityManager em;
	public SongDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void add(Song song) {
		
		DownloadPoster.download(song.getTitle(), song.getPoster());
		song.setPoster("rsc/posters/" + song.getTitle() + ".jpg");
		em.getTransaction().begin();
		em.persist(song);
		em.getTransaction().commit();
	}
	
	public Song findSongByTitle(String title) {
		
		return em.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
				.setParameter("title", title)
				.getSingleResult();
	}
	
	public List<Song> getAllSongs() {
		
		return em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
	}
	
}
