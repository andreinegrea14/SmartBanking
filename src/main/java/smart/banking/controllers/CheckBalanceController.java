package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/client.fxml"));
        Scene loginScene = new Scene(loginWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Client Menu");
        window.show();

    }
    public void FundsToBeAdded() throws NumberFormatException{
        try {
            if (fundsField.getText() == null || fundsField.getText().isEmpty() || Double.parseDouble(fundsField.getText()) == 0) {
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
}
