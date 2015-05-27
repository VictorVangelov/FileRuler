package fileruler.utils;

public enum GenreEnum {

	ACTION("Action"), 
	ANIMATION("Animation"), 
	COMEDY("Comedy"), 
	DOCUMENTARY("Documentary"),
	FAMILY("Family"),
	FILM_NOIR("Film-noir"),
	HORROR("Horror"),
	MUSICAL("Musical"),
	ROMANCE("Romance"),
	SPORT("Sport"),
	WAR("War"),
	ADVENTURE("Adventure"),
	BIOGRAPHY("Biography"),
	CRIME("Crime"),
	DRAMA("Drama"),
	FANTASY("Fantasy"),
	HISTORY("History"),
	MUSIC("Music"),
	MYSTERY("Mystery"),
	SCI_FI("Sci-Fi"),
	THRILLER("Thriller"),
	WESTERN("Western");
	
	final private String genre;
	
	private GenreEnum(String genre) {
		
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}
	
	
}
