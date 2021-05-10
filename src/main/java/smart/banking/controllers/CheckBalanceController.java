package smart.banking.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.services.ClientService;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.lang.Integer;

public class CheckBalanceController {
    @FXML
    public TextField fundsField;
    @FXML
    public Text clientMessage;
    @FXML
    private Text totalAmount;
    @FXML
    public void FundsToBeAdded() throws NumberFormatException{
        try {
            if (fundsField.getText() == null || fundsField.getText().isEmpty() || Integer.parseInt(fundsField.getText()) == 0) {
                clientMessage.setText("Total amount: " +String.valueOf(ClientService.getFunds(LoginController.client)));
                return;
            }
        }
        catch(NumberFormatException ex){
            clientMessage.setText(ex.getMessage());
        }
        ClientService.addFunds(LoginController.client, Integer.parseInt(fundsField.getText()));
        clientMessage.setText("Set new funds: " + String.valueOf(fundsField.getText()));
        totalAmount.setText("Total funds: " + String.valueOf(ClientService.getFunds(LoginController.client)));
    }

    public void cancel() throws IOException {
        Parent loginWindow = FXMLLoader.load(ClientController.class.getResource("/client.fxml"));
        Scene loginScene = new Scene(loginWindow);
        Stage window = new Stage();
        window.setScene(loginScene);
        window.setTitle("Client Menu");
        window.show();
        ClientController.stg.close();
    }
}
