package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.model.Client;
import smart.banking.services.ClientService;
import smart.banking.services.UserService;
import java.io.IOException;
import java.util.List;


public class RequestBankSupportController {
    public static Stage stg;
    @FXML
    private TextField message;
    @FXML
    private ListView messagesList;
    @FXML
    private Text list;
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/client.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Client Menu");
        window.show();
    }

    public void sendMessage() throws Exception {
        ClientService.sendMessage(LoginController.client, message.getText());
        ClientService.getContor(LoginController.client);
        message.setText("");
    }

    public void openListMenu(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/listofClientRequests.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("List of Client Requests");
        window.show();
    }

}
