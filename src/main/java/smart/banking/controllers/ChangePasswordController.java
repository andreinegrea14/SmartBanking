package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.Client;
import smart.banking.model.User;
import smart.banking.services.ClientService;
import smart.banking.services.UserService;

import java.io.IOException;

public class ChangePasswordController {
    public TextField usernameInput;
    @FXML
    private PasswordField newPassword;
    @FXML
    public Text error;

    public void goBacktoLogin(ActionEvent event) throws IOException {


        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 600, 525);
        Stage window = ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();

    }

    public void forgotPassword()  {
        if(usernameInput.getText() == null || usernameInput.getText().isEmpty()) {
            error.setText("Please introduce an username!");
            return;
        }
        if (!UserService.getUser(usernameInput.getText()).getUsername().equals(usernameInput.getText())) {
            error.setText("Please introduce a valid username!");
            return;
        }
        if(newPassword.getText() == null || newPassword.getText().isEmpty()){
            error.setText("Please introduce a password!");
            return;
        }
        if (UserService.getUser(usernameInput.getText()).getPassword().equals(UserService.getUser(usernameInput.getText()).getPassword())) {

            UserService.encodePassword(usernameInput.getText(), newPassword.getText());
            if (UserService.getUser(usernameInput.getText()).getPassword().equals(UserService.encodePassword(usernameInput.getText(), newPassword.getText()))) {
                error.setText("Your new password can not be the same with the old one!");
                return;
            }
            else {
                UserService.addPassword(usernameInput.getText(), newPassword.getText(), UserService.getUser(usernameInput.getText()).getRole());
                error.setText("Your password was successfully changed!");
            }

        }
    }
}