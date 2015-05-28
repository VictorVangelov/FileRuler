package fileruler.view;

import javafx.scene.image.ImageView;
import fileruler.model.Movie;

public class ImageViewCustom extends ImageView {
    private Movie movieSource;
    
    public ImageViewCustom(Movie movie) {
        this.movieSource = movie;
    }
    
    public Movie getMovieSource(){
        return this.movieSource;
    }
    
    public void setMovieSource(Movie m){
        this.movieSource = m;
    }
}
