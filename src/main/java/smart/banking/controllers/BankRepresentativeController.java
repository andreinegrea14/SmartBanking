package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import smart.banking.exceptions.UsernameAlreadyExists;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import smart.banking.model.User;
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

    public void handleLogout() throws IOException {
        Parent loginWindow = FXMLLoader.load(ClientController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 360, 525);
        Stage window = new Stage();
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();
        BankRepresentativeController.stg.close();
    }


    public void openListofRequests(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(BankRepresentativeController.class.getResource("/listofBankRequests.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("List of requests");
        window.show();

    }




}