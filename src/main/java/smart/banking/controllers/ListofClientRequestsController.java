package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.services.BankRepresentativeService;
import smart.banking.services.ClientService;

import javax.validation.Valid;
import java.io.IOException;

public class ListofClientRequestsController {
    @FXML
    private ListView listMessage;
    @FXML
    private ListView status;
    @FXML
    private ListView review;
    @FXML
    private String statusMessage;
    @FXML
    public void initialize() {
        showMessages();
        showStatus();
        showReviews();
    }

    public void showMessages() {
        listMessage.getItems().add("");
        listMessage.getItems().add("Requests list");
        for (int i = 0; i < ClientService.getContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getMessages()[i] == null) {
                listMessage.getItems();
            } else {
                listMessage.getItems().add(ClientService.getClient(LoginController.client).getMessages()[i]);
            }
        }
    }

    public void showReviews() {
        review.getItems().add("");
        review.getItems().add("Review");
        for (int i = 0; i < ClientService.getContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getReviews()[i] == null) {
                review.getItems().add("No review yet");
            } else {
                review.getItems().add(ClientService.getClient(LoginController.client).getReviews()[i]);
            }
        }
    }

    public void showStatus() {
        status.getItems().add("");
        status.getItems().add("Status");
        for(int i = 0; i < ClientService.getContor(LoginController.client); i++){
            if (ClientService.getClient(LoginController.client).getMessages()[i] == null) {
                listMessage.getItems();
            } else {
                if (ClientService.getClient(LoginController.client).getStatusReview()[i] == null) {
                    statusMessage = "PENDING";
                    status.getItems().add(statusMessage);
                } else if (ClientService.getClient(LoginController.client).getStatusReview()[i].equals("Accepted")) {
                    statusMessage = "ACCEPTED";
                    status.getItems().add(statusMessage);
                } else if (ClientService.getClient(LoginController.client).getStatusReview()[i].equals("Rejected")) {
                    statusMessage = "REJECTED";
                    status.getItems().add(statusMessage);
                } else {
                    statusMessage = "";
                    status.getItems().add(statusMessage);
                }
            }
        }
    }
    public void goBack(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/requestSupport.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Request Support");
        window.show();

    }

}
