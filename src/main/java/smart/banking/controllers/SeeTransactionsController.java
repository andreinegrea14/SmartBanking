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
import smart.banking.services.ClientService;

import java.io.IOException;
import java.util.Arrays;

public class SeeTransactionsController {
    @FXML
    private ListView transactionList;
    @FXML
    private ListView userList;
    @FXML
    private ListView status;
    @FXML
    private String statusMessage;
    private String transactionString;
    private String userString;
    private String statusString;

    public void initialize() {
        showTransactions();
        showUsers();
        showStatus();
    }

    public void getTransactions() {
        ClientService.getClient(LoginController.client).getTransactions();
    }

    public static String bold(String text) {
        return new StringBuffer().append("<b>").append(text).append("</b>").toString();
    }

    public void showTransactions() {
        transactionString = "Amount";
        Text text = new Text(30,80,transactionString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        transactionList.getItems().add(text);

        for (int i = 0; i < ClientService.getTransactionsContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getTransaction()[i] == null) {
                transactionList.getItems();
            } else {
                transactionList.getItems().add(ClientService.getClient(LoginController.client).getTransaction()[i] + " lei");

            }
        }
    }

    public void showUsers() {
        userString = "User";
        Text text = new Text(30,80,userString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        userList.getItems().add(text);
        for(int i = 0; i < ClientService.getUserTransactionContor(LoginController.client); i++){
            if (ClientService.getClient(LoginController.client).getUserTransaction()[i] == null) {
                userList.getItems();
            } else {
                userList.getItems().add(ClientService.getClient(LoginController.client).getUserTransaction()[i]);
            }
        }
    }

    public void showStatus() {
        statusString = "Status";
        Text text = new Text(30,80,statusString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        status.getItems().add(text);

        for(int i = 0; i < ClientService.getSendOrReceivedContor(LoginController.client); i++){

            if (ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 0) {
                status.getItems();
            } else {
                if (ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 1) {
                    statusMessage = "SENT";
                    Text text2 = new Text(30, 80, statusMessage);
                    text2.setFill(Color.valueOf("#F39C12"));
                    text2.setFont(newFont);
                    status.getItems().add(text2);
                } else if(ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 2) { {
                    statusMessage = "RECEIVED";
                    Text text3 = new Text(30, 80, statusMessage);
                    text3.setFill(Color.valueOf("#58D68D"));
                    text3.setFont(newFont);
                    status.getItems().add(text3);
                }
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
}
