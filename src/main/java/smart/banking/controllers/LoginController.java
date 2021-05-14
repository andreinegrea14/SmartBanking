package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import smart.banking.model.Client;
import smart.banking.services.BankRepresentativeService;
import smart.banking.services.ClientService;
import smart.banking.services.UserService;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginController {
    public static Stage stg;
    @FXML
    private Text loginMessage;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;

    @FXML
    public static String client;
    @FXML
    public static String bankRepresentative;

    public void openRegister(ActionEvent event) throws IOException {
        Parent registerWindow = FXMLLoader.load(ClientController.class.getResource("/register.fxml"));
        Scene registerScene = new Scene(registerWindow,360, 525);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(registerScene);
        window.setTitle("Register");
        window.show();
    }
    public void login() throws Exception {
        if(usernameInput.getText() == null || usernameInput.getText().isEmpty()) {
            loginMessage.setText("Please introduce an username!");
            return;
        }
        if(passwordInput.getText() == null || passwordInput.getText().isEmpty()){
            loginMessage.setText("Please introduce a password!");
            return;
        }
        if(UserService.checkUsers(usernameInput.getText(),passwordInput.getText())==1)
        { ClientController.openClientScreen();
            LoginController.client=usernameInput.getText();
            if (LoginController.client.equals(ClientService.getClient(LoginController.client).getUsername()) == false) {
                ClientService.addClient(LoginController.client, 0);
                ClientService.addClient(LoginController.client);
                BankRepresentativeService.addClient(usernameInput.getText());
            }
            return;}
        if(UserService.checkUsers(usernameInput.getText(),passwordInput.getText())==2)
        { BankRepresentativeController.openBankRepresentativeScreen();
            LoginController.bankRepresentative=usernameInput.getText();
            if (LoginController.bankRepresentative.equals(BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getUsername()) == false) {
                BankRepresentativeService.addBankRepresentative(LoginController.bankRepresentative);
            }
            return;}

        loginMessage.setText("Incorrect login!");
    }

}