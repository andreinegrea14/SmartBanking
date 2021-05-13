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
import smart.banking.services.ClientService;
import smart.banking.services.UserService;
import smart.banking.Main;
import java.io.IOException;

public class ClientController {
    public static  Stage stg;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Text registerMessage;

    public static void openClientScreen() throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/client.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window = new Stage();
        window.setScene(adminScene);
        window.setTitle("Client Panel");
        window.show();
        stg=window;
        Main.stg.close();
    }

    public void register() throws UsernameAlreadyExists {
        try {
            UserService.addUser(usernameInput.getText(), passwordInput.getText(), "Client");
            registerMessage.setText("Account created successfully!");
        } catch(UsernameAlreadyExists e)
        {
            registerMessage.setText(e.getMessage());
        }
    }

    public void handleLogout(ActionEvent event) throws IOException {


        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow,360, 525);

        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.show();

    }



    public void openCheckBalance(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/checkbalance.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Check Balance");
        window.show();
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