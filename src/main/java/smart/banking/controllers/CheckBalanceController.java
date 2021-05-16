package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.services.ClientService;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.lang.Double;

public class CheckBalanceController {
    @FXML
    public TextField fundsField;
    @FXML
    public Text clientMessage;
    @FXML
    private Text totalAmount;
    @FXML
    public void handleCloseButtonAction(ActionEvent event) throws IOException {
        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 600, 525);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();

    }
    public void FundsToBeAdded() throws NumberFormatException{
        try {
            if (fundsField.getText() == null || fundsField.getText().isEmpty() || Double.parseDouble(fundsField.getText()) == 0) {
                System.out.println("Total amount: " +String.valueOf(ClientService.getFunds(LoginController.client)) + " lei");
                clientMessage.setText("Total amount: " +String.valueOf(ClientService.getFunds(LoginController.client)) + " lei");
                return;
            }
        }
        catch(NumberFormatException ex){
            clientMessage.setText(ex.getMessage());
        }
        ClientService.addFunds(LoginController.client, Double.parseDouble(fundsField.getText()));
        clientMessage.setText("Set new funds: " + String.valueOf(fundsField.getText()) + " lei");
        totalAmount.setText("Total funds: " + String.valueOf(ClientService.getFunds(LoginController.client)) + " lei");
    }

    public void transferFundsOnPress(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/transferFunds.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Transfer Funds");
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
