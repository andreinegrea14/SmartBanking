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

    public void handleLogout(ActionEvent event) throws IOException {
        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow,600, 525);

        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
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

    public void transferFundsOnPress(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/transferFunds.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Transfer Funds");
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
