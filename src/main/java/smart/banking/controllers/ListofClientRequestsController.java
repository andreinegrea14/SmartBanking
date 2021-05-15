package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    private String messageString;
    private String reviewString;
    private String statusM;
    private String reviews;
    public void initialize() {
        showMessages();
        showStatus();
        showReviews();
    }

    public void showMessages() {
        messageString = "Requests list";
        Text text = new Text(30,80,messageString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        listMessage.getItems().add(text);
        for (int i = 0; i < ClientService.getContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getMessages()[i] == null) {
                listMessage.getItems();
            } else {
                listMessage.getItems().add(ClientService.getClient(LoginController.client).getMessages()[i]);
            }
        }
    }


    public void showReviews() {
        reviewString = "Review";
        Text text = new Text(30,80,reviewString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        review.getItems().add(text);
        for (int i = 0; i < ClientService.getContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getReviews()[i] == null) {
                review.getItems().add("No review yet");
            } else {
                review.getItems().add(ClientService.getClient(LoginController.client).getReviews()[i]);
            }
        }
    }

    public void showStatus() {


        statusM = "Status";
        Text text = new Text(30,80,statusM);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        status.getItems().add(text);
        for(int i = 0; i < ClientService.getContor(LoginController.client); i++){
            if (ClientService.getClient(LoginController.client).getMessages()[i] == null) {
                listMessage.getItems();
            } else {
                if (ClientService.getClient(LoginController.client).getStatusReview()[i] == null) {

                    statusMessage = "PENDING";
                    Text text2 = new Text(30, 80, statusMessage);
                    text2.setFill(Color.valueOf("#F39C12"));
                    text2.setFont(newFont);
                    status.getItems().add(text2);
                } else if (ClientService.getClient(LoginController.client).getStatusReview()[i].equals("Accepted")) {
                    statusMessage = "ACCEPTED";
                    Text text2 = new Text(30, 80, statusMessage);
                    text2.setFill(Color.valueOf("#27AE60"));
                    text2.setFont(newFont);
                    status.getItems().add(text2);

                } else if (ClientService.getClient(LoginController.client).getStatusReview()[i].equals("Rejected")) {
                    statusMessage = "REJECTED";
                    Text text2 = new Text(30, 80, statusMessage);
                    text2.setFill(Color.valueOf("#E74C3C"));
                    text2.setFont(newFont);
                    status.getItems().add(text2);

                } else {
                    statusMessage = "";
                    status.getItems().add(statusMessage);
                }
            }
        }
    }
    public void transferFundsOnPress(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/transferFunds.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Transfer Funds");
        window.show();
    }

    public void handleCloseButtonAction(ActionEvent event) throws IOException {
        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 600, 525);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();
    }

    public void openCheckBalance(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/checkbalance.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Check Balance");
        window.show();
    }


    public void requestSupport(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/requestSupport.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Request Support");
        window.show();

    }
    public void seeTransactions(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/seeTransactions.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("List of all transactions");
        window.show();

    }

}
