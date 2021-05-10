package smart.banking.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class RequestBankSupportController {
    public static Stage stg;
    @FXML
    private TextField message;
    @FXML
    public void goBack() throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/client.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window = new Stage();
        window.setScene(adminScene);
        window.setTitle("Client Menu");
        window.show();
        ClientController.stg.close();
    }

}
