package smart.banking.controllers;

import smart.banking.exceptions.UsernameAlreadyExists;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.services.UserService;
import smart.banking.Main;
import java.io.IOException;

public class BankRepresentativeController {
    public static  Stage stg;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Text registerMessage;

    public static void openBankRepresentativeScreen() throws IOException {

        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/bankrepresentative.fxml"));

        Scene adminScene = new Scene(adminWindow);

        Stage window = new Stage();

        window.setScene(adminScene);
        window.setTitle("Bank Representative Panel");
        window.show();
        stg=window;
        Main.stg.close();



    }

    public void register() throws UsernameAlreadyExists {
        try {
            UserService.addUser(usernameInput.getText(), passwordInput.getText(), "Bank Representative");
            registerMessage.setText("Account created successfully!");
        }catch(UsernameAlreadyExists e)
        {
            registerMessage.setText(e.getMessage());
        }
    }

}