package fileruler.view;

import javafx.scene.image.ImageView;

public class ImageViewCustom<T> extends ImageView {
    private T source;
    
    public ImageViewCustom(T source) {
        this.source = source;
    }
    
    public T getMovieSource(){
        return this.source;
    }
    
    public void setMovieSource(T source){
        this.source = source;
    }
}
