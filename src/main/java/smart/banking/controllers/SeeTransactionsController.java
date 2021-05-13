package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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
    @FXML
    public void initialize() {
        showTransactions();
        showUsers();
        showStatus();
    }

    public void goBack(ActionEvent event) throws IOException {


        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/client.fxml"));
        Scene loginScene = new Scene(loginWindow);
        Stage window = ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Client Menu");
        window.show();

    }

    public void getTransactions() {
        ClientService.getClient(LoginController.client).getTransactions();
    }

    public void showTransactions() {
        transactionList.getItems().add("");
        transactionList.getItems().add("Transactions list");
        for (int i = 0; i < ClientService.getTransactionsContor(LoginController.client); i++) {
            if (ClientService.getClient(LoginController.client).getTransaction()[i] == null) {
                transactionList.getItems();
            } else {
                transactionList.getItems().add(ClientService.getClient(LoginController.client).getTransaction()[i] + " lei");

            }
        }
    }

    public void showUsers() {
        userList.getItems().add("");
        userList.getItems().add("User");
        for(int i = 0; i < ClientService.getUserTransactionContor(LoginController.client); i++){
            if (ClientService.getClient(LoginController.client).getUserTransaction()[i] == null) {
                userList.getItems();
            } else {
                userList.getItems().add(ClientService.getClient(LoginController.client).getUserTransaction()[i]);
            }
        }
    }

    public void showStatus() {
        status.getItems().add("");
        status.getItems().add("Status");
        for(int i = 0; i < ClientService.getSendOrReceivedContor(LoginController.client); i++){
            if (ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 0) {
                status.getItems();
            } else {
                if (ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 1) {
                    statusMessage = "SENT";
                    status.getItems().add(statusMessage);
                } else if(ClientService.getClient(LoginController.client).getReceivedOrSent()[i] == 2) { {
                    statusMessage = "RECEIVED";
                    status.getItems().add(statusMessage);
                }
                }
            }
        }
    }
}
