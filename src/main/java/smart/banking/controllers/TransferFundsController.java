package smart.banking.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import smart.banking.services.ClientService;
import java.lang.Integer;
import java.lang.Double;

public class TransferFundsController {
    @FXML
    private ChoiceBox currency;
    @FXML
    public static  Stage stg;

    @FXML
    private TextField funds;
    @FXML
    private TextField username;
    @FXML
    private Text verifyFundsMessage;
    @FXML
    public void initialize() {
        currency.getItems().addAll("EURO", "RON", "USD");
    }

    public void goBack() throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/client.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window = new Stage();
        window.setScene(adminScene);
        window.setTitle("Client Menu");
        window.show();
        ClientController.stg.close();
    }
    public double convertCurrency(double funds, String currency){
        if (currency.equals("EURO")) {
            return 4.93 * funds;
        } else if (currency.equals("USD")) {
            return 4.06 * funds;
        }
        return funds;
    }
    public void transfer() throws IOException {
        ClientService.addFunds(username.getText(), convertCurrency(Double.parseDouble((funds.getText())), currency.getValue().toString()));
    }
    public void verifyFunds() throws Exception {
        if (funds.getText() == null || funds.getText().isEmpty() ||Double.parseDouble(funds.getText()) == 0) {
            verifyFundsMessage.setText("Insuficient funds!");
            return;
        }
        if (username.getText().isEmpty() || username.getText() == null || (username.getText().equals(ClientService.getClient(username.getText()).getUsername())) == false) {
            verifyFundsMessage.setText("Username does not exist!");
            return;
        }
        if (currency.getValue() == null) {
            verifyFundsMessage.setText("Please select currency type!");
            return;
        }
        transfer();
        verifyFundsMessage.setText("Transfer successed!");
    }


}
