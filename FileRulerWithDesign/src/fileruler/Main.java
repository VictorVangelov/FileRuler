package fileruler;

import java.io.IOException;

import fileruler.view.BaseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane layout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FileRuler")
        initializeLayout();
        initializeInnerLayout();
        
    }

    private void initializeInnerLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/InnerLayout.fxml"));
            AnchorPane anchorPane = loader.load();
            layout.setCenter(anchorPane);
            BaseController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void initializeLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Layout.fxml"));
            this.layout = loader.load();
            Scene scene = new Scene(layout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
}
