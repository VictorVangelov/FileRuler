package fileruler.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;




import org.apache.lucene.queryParser.ParseException;




import fileruler.Main;
import fileruler.apis.Lucene.LuceneTester;
import fileruler.dao.DAOManager;
import fileruler.dao.FileDAO;
import fileruler.dao.ImageDAO;
import fileruler.dao.SongDAO;
import fileruler.model.Movie;
import fileruler.model.Song;
import fileruler.utils.FileUtils;
import fileruler.utils.MovieUtils;import fileruler.utils.VoiceRecognitionUtils;


public class BaseController {
    private DAOManager<Movie> managerDAO = new DAOManager<>();
    private final String posterURL = "rsc/posters/";
    private final String luceneURLImagesIndexDir = "rsc/lucene/images/indexDIR";
    private final String luceneURLImages = "rsc/lucene/images";
    private final String luceneURLTextsIndexDIR = "rsc/lucene/texts/indexDIR";
    private final String luceneImagesImagga = "D:\\FileRulersearch\\images";

    private final String luceneURLITextsDIR = "rsc/lucene/texts/";
    private final String luceneURLITexts = "D:\\FileRulersearch";
    @FXML
    private Label releaseDateLabel;
    @FXML
    private TextField textSearch;
    @FXML
    private Label actors;
    @FXML
    private Label producer;
    @FXML
    private Label director;
    @FXML
    private Label genre;
    @FXML
    private Label duration;
    @FXML
    private Label country;
    @FXML
    private GridPane gridDetails;
    private Thread listenerSpeaker;
    private int numberLIstening = 0;
    @FXML
    private Button listenBtn;
    @FXML
    private GridPane gridSearchDetails;

    @FXML
    private TextField moviesActorsSearchDet;

    @FXML
    private TextField moviesDirectorsSearchDet;

    @FXML
    private TextField moviesYearSearchDet;

    @FXML
    private TextField moviesTitleSearchDet;

    @FXML
    private GridPane songGridDetails;

    @FXML
    private GridPane songGridSearchDetails;

    private GridPane selectedGridPane;

    @FXML
    private Button buttonSearch;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Label songTitle;
    @FXML
    private Label songAthor;
    @FXML
    private Label songAlbum;

    @FXML
    private Label songGenre;

    @FXML
    private Label songLength;

    @FXML
    private TextField songArtistsSearchDet;

    @FXML
    private TextField songSongsSearchDet;

    @FXML
    private TextField songAlbumsSearchDet;

    @FXML
    private GridPane picturesGridSearhDet;

    @FXML
    private TextField picturesInvPeopleSearchDet;

    @FXML
    private TextField picturesTagsSearchDet;

    @FXML
    private GridPane textsSearchDetails;

    @FXML
    private TextField textsNamesSearchDet;

    @FXML
    private TextField textsThemesSearchDet;

    private int startPositionImageX = 20;
    private int startPositionImageY = 20;

    private int paneMaxImagesCount = 400;

    private Map<ImageViewCustom, Label> imagesDynamicallyCreated = new HashMap<>();

    private Map<String, List<TextField>> listNodesAllSearchTypes = new HashMap<>();

    @FXML
    private Pane imagePane;

    private Main mainApp;
    private String searchType;

    public BaseController() {
        managerDAO.openDBConn();
    }

    @FXML
    private void initialize() {
        showMoviesDetails(null);
        ObservableList<String> itemsCombo = FXCollections.observableArrayList();
        itemsCombo.addAll(SearchTypeEnum.Movies.name(), SearchTypeEnum.Texts.name(), SearchTypeEnum.Images.name(),
                SearchTypeEnum.Songs.name());
        combo.setItems(itemsCombo);
        combo.valueProperty().addListener(
                (fullObject, t, selectedName) -> {
                    this.searchType = selectedName;
                    switch (this.searchType) {
                        case "Movies": {
                            selectedGridPane = gridSearchDetails;
                            showElement(gridSearchDetails);
                            hideAllElements(songGridSearchDetails, songGridDetails, gridDetails, picturesGridSearhDet,
                                    textsSearchDetails);
                        }
                            break;
                        case "Texts": {
                            selectedGridPane = textsSearchDetails;
                            showElement(textsSearchDetails);
                            hideAllElements(songGridSearchDetails, songGridDetails, gridDetails, gridSearchDetails,
                                    picturesGridSearhDet);
                        }
                            break;
                        case "Images": {
                            selectedGridPane = picturesGridSearhDet;
                            showElement(picturesGridSearhDet);
                            hideAllElements(songGridSearchDetails, songGridDetails, gridDetails, gridSearchDetails,
                                    textsSearchDetails);
                        }
                            break;
                        case "Songs": {
                            selectedGridPane = songGridSearchDetails;
                            showElement(songGridSearchDetails);
                            hideAllElements(gridSearchDetails, songGridDetails, gridDetails, picturesGridSearhDet,
                                    textsSearchDetails);
                        }
                            break;
                        default:
                            break;
                    }
                });

        ObservableList<TextField> songsSearchDetailsList = FXCollections.observableArrayList();
        songsSearchDetailsList.addAll(songAlbumsSearchDet, songSongsSearchDet, songArtistsSearchDet);
        listNodesAllSearchTypes.put(SearchTypeEnum.Songs.name(), songsSearchDetailsList);

        ObservableList<TextField> moviesSearchDetailsList = FXCollections.observableArrayList();
        moviesSearchDetailsList.addAll(moviesActorsSearchDet, moviesDirectorsSearchDet, moviesTitleSearchDet,
                moviesTitleSearchDet, moviesYearSearchDet);
        listNodesAllSearchTypes.put(SearchTypeEnum.Movies.name(), moviesSearchDetailsList);

        ObservableList<TextField> textsSearchDetailsList = FXCollections.observableArrayList();
        textsSearchDetailsList.addAll(textsNamesSearchDet, textsThemesSearchDet);
        listNodesAllSearchTypes.put(SearchTypeEnum.Texts.name(), textsSearchDetailsList);

        ObservableList<TextField> picturesSearchDetailsList = FXCollections.observableArrayList();
        picturesSearchDetailsList.addAll(picturesInvPeopleSearchDet, picturesTagsSearchDet);
        listNodesAllSearchTypes.put(SearchTypeEnum.Images.name(), picturesSearchDetailsList);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        this.mainApp.getPrimaryStage().setOnCloseRequest(event -> {
            managerDAO.closeDBConn();
        });
    }

    private void showMoviesDetails(Movie movie) {
        if (movie != null) {
            this.actors.setText(movie.getActors());
            this.country.setText(movie.getCountry());
            this.director.setText(movie.getDirectors());
            this.duration.setText(String.valueOf(movie.getRuntime()));
            this.genre.setText(movie.getGenres().toString());
            this.producer.setText(movie.getWriters());
            this.releaseDateLabel.setText(movie.getReleased());
        } else {
            this.country.setText("");
            this.director.setText("");
            this.duration.setText("");
            this.genre.setText("");
            this.producer.setText("");
            this.releaseDateLabel.setText("");
        }
    }

    private void showSongsDetails(Song song) {
        if (song != null) {
            this.songTitle.setText(song.getTitle());
            this.songLength.setText(song.getLength());
            this.songAlbum.setText(song.getAlbum());
            this.songAthor.setText(song.getAuthor());
            this.songGenre.setText(song.getGenre());
        } else {
            this.songTitle.setText("");
            this.songLength.setText("");
            this.songAlbum.setText("");
            this.songAthor.setText("");
            this.songGenre.setText("");
        }
    }

    @FXML
    private void handleOnClick() {
        StringBuilder name = new StringBuilder();
        name.append(textSearch.getText() + " ");
        List<TextField> searchDetailsTexts = null;
        if (this.searchType != null) {
            searchDetailsTexts = this.listNodesAllSearchTypes.get(this.searchType);
        }
        for (TextField txtField : searchDetailsTexts) {
            if (!txtField.getText().equals("")) {
                name.append(txtField.getText() + " ");
            }
        }
        searchHandler(name.toString());

    }

    private <T> void addTextsToScene(String URL, String labelText, double x, double y, T imageSource) {
        ImageViewCustom<T> imageView = new ImageViewCustom<>(imageSource);
        File file = new File(URL);
        Image image = new Image(file.toURI().toString(), 78, 79, false, false);
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setVisible(true);
        Label lblItem = new Label("");
        lblItem.setText(((FileDAO) imageSource).getFileName());
        lblItem.setLayoutX(x + 10);
        lblItem.setLayoutY(100 + y);
        lblItem.setVisible(true);
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(lblItem);
        imageView.setOnMouseClicked(event -> {
            //showElement(songGridDetails);
            hideAllElements(gridSearchDetails, gridDetails, songGridSearchDetails,songGridDetails);
            try {
                Desktop.getDesktop().open(new File(((FileDAO) imageSource).getFilePath()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        imagesDynamicallyCreated.put(imageView, lblItem);
    }

    private <T> void addSongsToScene(String URL, String labelText, double x, double y, T imageSource) {
        ImageViewCustom<T> imageView = new ImageViewCustom<>(imageSource);
        File file = new File(URL);
        Image image = new Image(file.toURI().toString(), 78, 79, false, false);
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setVisible(true);
        Label lblItem = new Label("");
        lblItem.setText(((Song) imageSource).getTitle());
        lblItem.setLayoutX(x + 10);
        lblItem.setLayoutY(100 + y);
        lblItem.setVisible(true);
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(lblItem);
        imageView.setOnMouseEntered(event -> {
            hideAllElements(gridSearchDetails, gridDetails, songGridSearchDetails);
            showElement(songGridDetails);
            showSongsDetails((Song) imageView.getMovieSource());
        });
        imageView.setOnMouseClicked(event -> {
            showElement(songGridDetails);
            hideAllElements(gridSearchDetails, gridDetails, songGridSearchDetails);
            try {
                Desktop.getDesktop().open(new File(((Song) imageSource).getFilePath()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        imageView.setOnMouseExited(event -> {
            showElement(songGridSearchDetails);
            hideAllElements(gridDetails, songGridDetails, gridSearchDetails);
            showSongsDetails(null);
        });

        imagesDynamicallyCreated.put(imageView, lblItem);
    }

    private <T> void addItemsToScene(String URL, String labelText, double x, double y, T imageSource) {
        ImageViewCustom<T> imageView = new ImageViewCustom<>(imageSource);
        File file = new File(URL);
        Image image = new Image(file.toURI().toString(), 78, 79, false, false);
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setVisible(true);
        Label lblItem = new Label("");
        lblItem.setText(((Movie) imageSource).getTitle());
        lblItem.setLayoutX(x + 10);
        lblItem.setLayoutY(100 + y);
        lblItem.setVisible(true);
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(lblItem);
        imageView.setOnMouseEntered(event -> {
            hideAllElements(gridSearchDetails, songGridDetails, songGridSearchDetails);
            showElement(gridDetails);
            showMoviesDetails((Movie) imageView.getMovieSource());
        });
        imageView.setOnMouseClicked(event -> {
            showElement(gridDetails);
            hideAllElements(gridSearchDetails, songGridDetails, songGridSearchDetails);
            try {
                Desktop.getDesktop().open(new File("rsc/movies/" + ((Movie) imageSource).getTitle() + ".avi"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        imageView.setOnMouseExited(event -> {
            showElement(gridSearchDetails);
            hideAllElements(gridDetails, songGridDetails, songGridSearchDetails);
            showMoviesDetails(null);
        });

        imagesDynamicallyCreated.put(imageView, lblItem);
    }
 
    private <T> void addImagesToScene(String URL, String labelText, double x, double y, T imageSource){
        ImageViewCustom<T> imageView = new ImageViewCustom<>(imageSource);
        File file = new File(URL);
        Image image = new Image(file.toURI().toString(), 78, 79, false, false);
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setVisible(true);
        Label lblItem = new Label("");
        lblItem.setText(((ImageDAO) imageSource).getName());
        lblItem.setLayoutX(x + 10);
        lblItem.setLayoutY(100 + y);
        lblItem.setVisible(true);
        imagePane.getChildren().add(imageView);
        imagePane.getChildren().add(lblItem);
        imageView.setOnMouseClicked(event -> {
            //showElement(songGridDetails);
            hideAllElements(gridSearchDetails, gridDetails, songGridSearchDetails,songGridDetails);
            try {
                Desktop.getDesktop().open(new File(((ImageDAO) imageSource).getPath()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        imagesDynamicallyCreated.put(imageView, lblItem);
    }
    
    private void findMovies(String value) {
        initDynamicImgLocations();
        if (value.contains("vin")) {
            value = "diesel";
        }
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        List<Movie> existingMovie = (List<Movie>) managerDAO.selectSpecificRecords(value);
        if (!existingMovie.isEmpty()) {
            ObservableList<Movie> existingMovieObserv = FXCollections.observableArrayList(existingMovie);
            for (Movie movie : existingMovieObserv) {
                addItemsToScene(posterURL + movie.getTitle() + ".jpg", "test", startPositionImageX,
                        startPositionImageY, movie);
                startPositionImageX += 100;
                if (startPositionImageX >= paneMaxImagesCount) {
                    startPositionImageX = 20;
                    startPositionImageY += 120;
                }
            }
        } else {
            movies.add(MovieUtils.findMovieByName(value));
            managerDAO.addDataToDB(movies);
            ObservableList<Movie> moviesObserv = FXCollections.observableArrayList(movies);
            for (Movie movie : moviesObserv) {
                addItemsToScene(posterURL + movie.getTitle() + ".jpg", "test", startPositionImageX,
                        startPositionImageY, movie);
                startPositionImageX += 100;
                if (startPositionImageX >= paneMaxImagesCount) {
                    startPositionImageX = 20;
                    startPositionImageY += 120;
                }
            }
        }
    }

    private void showElement(GridPane control) {
        control.setVisible(true);
    }

    private void showAllElements(Node... controls) {
        for (int i = 0; i < controls.length; i++) {
            controls[i].setVisible(true);
        }
    }

    private void hideAllElements(Node... controls) {
        for (int i = 0; i < controls.length; i++) {
            controls[i].setVisible(false);
        }
    }

    private void hideElement(GridPane control) {
        control.setVisible(false);
    }

    private void searchHandler(String value) {

        switch (this.searchType) {
            case "Movies": {
                findMovies(value);
            }
                break;
            case "Texts": {
                findTexts(value);
            }
                break;
            case "Images":{
                findImages(value);
            }
                break;
            case "Songs": {
                findSong(value);
            }
                break;
            default:
                break;
        }

    }

    private void findImages(String value) {
        initDynamicImgLocations();
        LuceneTester lucene = new LuceneTester(luceneURLImagesIndexDir, luceneImagesImagga);
        List<ImageDAO> allFiles = new ArrayList<>();
        try {
            lucene.createIndex();
            LinkedList<String> urlsFound = lucene.search(value);
            for (String image : urlsFound) {
               String n =  new File(image).getName().toString();
                String imageName = new File(image).getName().substring(0, n.indexOf('.'))+".jpg";
                ImageDAO im = new ImageDAO(imageName, posterURL+imageName);
                allFiles.add(im);
            }
            for (ImageDAO img : allFiles) {
                addImagesToScene(img.getPath(), value, startPositionImageX, startPositionImageY, img);
                startPositionImageX += 100;
                if (startPositionImageX >= paneMaxImagesCount) {
                    startPositionImageX = 20;
                    startPositionImageY += 120;
                }
            }
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void findTexts(String value) {
        initDynamicImgLocations();
        LuceneTester luceneGOD = new LuceneTester(luceneURLTextsIndexDIR, luceneURLITexts);
        List<FileDAO> allFiles = new ArrayList<>();
        try {
            luceneGOD.createIndex();
            LinkedList<String> urlsFound = luceneGOD.search(value);
            for (String url : urlsFound) {
                String fileName = FileUtils.getFileName(url);
                FileDAO f = new FileDAO(fileName, url);
                String encodedURL = FileUtils.encode(url);
                FileUtils.copyFromTo(url, luceneURLITextsDIR + encodedURL);
                allFiles.add(f);
            }
            for (FileDAO file : allFiles) {
                addTextsToScene(posterURL+"text.png", file.getFileName(), startPositionImageX, startPositionImageY, file);
                startPositionImageX += 100;
                if (startPositionImageX >= paneMaxImagesCount) {
                    startPositionImageX = 20;
                    startPositionImageY+= 120;
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void findSong(String value) {
        initDynamicImgLocations();
        ObservableList<Song> songs = FXCollections.observableArrayList();
        SongDAO sDAO = new SongDAO(managerDAO.getManager());
        // Need fixes...
        Song searchSong = sDAO.findSongByTitle(value);
        // Song searchSong = new Song("Test", "Test", "Test", "Test", "Test",
        // "Test");
        if (searchSong != null) {
            addSongsToScene(posterURL + searchSong.getTitle() + ".jpg", searchSong.getTitle(), startPositionImageX,
                    startPositionImageY, searchSong);
        }
    }

    @FXML
    private void textBoxOnChange() {
        StringBuilder name = new StringBuilder();
        name.append(textSearch.getText() + " ");
        List<TextField> searchDetailsTexts = null;
        if (this.searchType != null) {
            searchDetailsTexts = this.listNodesAllSearchTypes.get(this.searchType);
        }
        for (TextField txtField : searchDetailsTexts) {
            if (!txtField.getText().equals("")) {
                name.append(txtField.getText() + " ");
            }
        }
        searchHandler(name.toString());
    }

    @FXML
    private void textOnKeyPressed() {

    }

    @FXML
    private void textBoxOnMouseClicked() {
        switch (this.searchType) {
            case "Movies": {
                selectedGridPane = gridSearchDetails;
                showElement(gridSearchDetails);
                hideAllElements(songGridSearchDetails, songGridDetails, gridDetails);
            }
                break;
            case "Texts":
                break;
            case "Images":
                break;
            case "Songs": {
                selectedGridPane = songGridSearchDetails;
                showElement(songGridSearchDetails);
                hideAllElements(gridSearchDetails, songGridDetails, gridDetails);
            }
                break;
            default:
                break;
        }
        showMoviesDetails(null);
        showSongsDetails(null);
        for (Entry<ImageViewCustom, Label> entryImage : this.imagesDynamicallyCreated.entrySet()) {
            entryImage.getKey().setVisible(false);
            entryImage.getValue().setVisible(false);
        }
    }

    @FXML
    private void buttonOnMouseClicked(){
        numberLIstening++;
        if (numberLIstening % 2 != 0) {
            listenerSpeaker = new Thread(() -> {
                String result = VoiceRecognitionUtils.record();
                this.textSearch.setText(result);
            });
            listenerSpeaker.start();
        }
        else{
            numberLIstening = 0;
            if (listenerSpeaker != null) {
                this.listenerSpeaker.stop();
            }
        }
        
    }
    
    @FXML 
    private void buttonOnMouseReleased(){
        if (listenerSpeaker != null) {
            listenerSpeaker.stop();
        }
    }

    private void initDynamicImgLocations() {
        this.startPositionImageX = 25;
        this.startPositionImageY = 25;
    }
}
