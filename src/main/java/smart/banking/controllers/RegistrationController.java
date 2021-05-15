package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.services.UserService;

import java.io.IOException;


public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;


    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Bank Representative");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            if(usernameField.getText() == null || usernameField.getText().isEmpty()) {
                registrationMessage.setText("Please introduce an username!");
                return;
            }
            if(passwordField.getText() == null || passwordField.getText().isEmpty()){
                registrationMessage.setText("Please introduce a password!");
                return;
            }
            if(role.getValue() == null || role.getItems().isEmpty()){
                registrationMessage.setText("Please select your role!");
                return;
            }
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExists e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    public void goBack(ActionEvent event) throws IOException {
        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 600, 525);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();

    }
}
