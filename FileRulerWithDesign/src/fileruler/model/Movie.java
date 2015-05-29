package fileruler.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 4321978985986255763L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String year;
	private String released;
	private String runtime;
	private String genres;
	private String directors;
	private String writers;
	private String actors;
	private String plot;
	private String country;
	private String imdbRating;
	private String imdbVotes;
	private String poster;
	private String filePath;
	

	public Movie() {
		super();
	}

	public Movie(String title, String year, String released, String runtime,
			String genres, String directors, String writers, String actors,
			String plot, String country, String imdbRating,
			String imdbVotes) {
		super();
		this.title = title;
		this.year = year;
		this.released = released;
		this.runtime = runtime;
		this.genres = genres;
		this.directors = directors;
		this.writers = writers;
		this.actors = actors;
		this.plot = plot;
		this.country = country;
		this.imdbRating = imdbRating;
		this.imdbVotes = imdbVotes;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    

    public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year
				+ ", released=" + released + ", runtime=" + runtime
				+ ", genres=" + genres + ", directors=" + directors
				+ ", writers=" + writers + ", actors=" + actors + ", plot="
				+ plot + ", country=" + country + ", imdbRating=" + imdbRating
				+ ", imdbVotes=" + imdbVotes + ", poster=" + poster
				+ ", filePath=" + filePath + "]";
	}

	
	
	
}