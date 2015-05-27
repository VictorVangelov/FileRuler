package fileruler.view;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import fileruler.Main;
import fileruler.dao.DAOManager;
import fileruler.model.Movie;
import fileruler.utils.MovieUtils;

public class BaseController {
    private DAOManager<Movie> managerDAO = new DAOManager<>();
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
    @FXML
    private GridPane gridSearchDetails;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<Movie> tableMovies;
    @FXML
    private TableColumn<Movie, String> nameColumn;
    @FXML
    private TableColumn<Movie, String> valueColumn;
    @FXML
    private ComboBox<String> combo;

    private Main mainApp;
    private String searchType;

    public BaseController() {
        managerDAO.openDBConn();
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        valueColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDirectors()));
        showMoviesDetails(null);

        tableMovies.getSelectionModel().selectedItemProperty()
                .addListener((observer, oldValue, newValue) -> showMoviesDetails(newValue));

        ObservableList<String> itemsCombo = FXCollections.observableArrayList();
        itemsCombo.addAll("Movies", "Texts", "Images", "Songs");
        combo.setItems(itemsCombo);

        combo.valueProperty().addListener((fullObject, t, selectedName) -> {
            this.searchType = selectedName;
        });
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
            this.genre.setText(movie.getGenres());
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

    @FXML
    private void handleOnClick() {
        String name = textSearch.getText();
        searchHandler(name);

    }

    private void findMovies(String value) {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        List<Movie> existingMovie = (List<Movie>) managerDAO.selectSpecificRecords(value);
        if (!existingMovie.isEmpty()) {
            ObservableList<Movie> existingMovieObserv = FXCollections.observableArrayList(existingMovie);
            tableMovies.setItems(existingMovieObserv);
            hideElement(gridSearchDetails);
            showElement(gridDetails);
        } else {
            movies.add(MovieUtils.findMovieByName(value));
            managerDAO.addDataToDB(movies);
            ObservableList<Movie> moviesObserv = FXCollections.observableArrayList(movies);

            hideElement(gridSearchDetails);
            showElement(gridDetails);
            tableMovies.setItems(moviesObserv);
        }
    }

    private void showElement(GridPane control) {
        control.setVisible(true);
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
            case "Texts":
                break;
            case "Images":
                break;
            case "Songs":
                break;
            default:
                break;
        }

    }

    @FXML
    private void textBoxOnChange() {
        String name = textSearch.getText();
        searchHandler(name);
    }
    
    @FXML 
    private void textOnKeyPressed(){
        
    }
}
