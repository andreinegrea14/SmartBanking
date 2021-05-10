package smart.banking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import smart.banking.services.ClientService;
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    public static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stg=primaryStage;
        initDirectory();
        UserService.initDatabase();
        ClientService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Smart-Banking");
        primaryStage.setScene(new Scene(root, 360, 525));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
