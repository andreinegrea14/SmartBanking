package smart.banking.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import smart.banking.services.UserService;
import javafx.scene.text.Text;
public class LoginController {
    public static Stage stg;
    @FXML
    private Text loginMessage;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;

  /*  public void initialize() {

        roleInput.getItems().setAll("Client", "Bank Representative");
    }*/

    public void openRegister() throws Exception {

        Parent registerWindow = FXMLLoader.load(getClass().getResource("/register.fxml"));
        Scene registerScene = new Scene(registerWindow);

        Stage window = new Stage();

        window.setScene(registerScene);
        window.setTitle("Register");
        window.show();
    }
    public void login() throws Exception {
        if(usernameInput.getText() == null || usernameInput.getText().isEmpty()) {
            loginMessage.setText("Please introduce an username!");
            return;
        }
        if(UserService.checkUsers(usernameInput.getText(),passwordInput.getText())==1)
        { ClientController.openClientScreen();
        return;}
        if(UserService.checkUsers(usernameInput.getText(),passwordInput.getText())==2)
        { BankRepresentativeController.openBankRepresentativeScreen();
            return;}

        loginMessage.setText("Incorrect login!");
    }

}