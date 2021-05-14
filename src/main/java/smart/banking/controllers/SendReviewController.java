package smart.banking.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import smart.banking.model.Client;
import smart.banking.services.BankRepresentativeService;
import smart.banking.services.ClientService;

import java.io.IOException;

public class SendReviewController {
    @FXML
    private ChoiceBox review;
    @FXML
    private TextField sendReview;
    @FXML
    public void initialize() {
        review.getItems().addAll("Accepted", "Rejected");
    }
    @FXML

    public void sendOnPress(ActionEvent event) throws IOException {
        if(review.getValue() == null || review.getValue().toString().isEmpty()){
            goBack(event);
        }
        else {
            ClientService.sendReviews(ListofBankRequestsController.clientName, sendReview.getText(), review.getValue().toString(), ListofBankRequestsController.requestIndex);
            goBack(event);
        }

    }
    public void goBack(ActionEvent event) throws  IOException{
        Parent adminWindow = FXMLLoader.load(BankRepresentativeController.class.getResource("/listofBankRequests.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("List Requests");
        window.show();
    }


}
