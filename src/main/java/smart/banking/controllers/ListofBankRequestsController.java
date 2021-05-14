package smart.banking.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import smart.banking.services.BankRepresentativeService;
import smart.banking.services.ClientService;
import java.io.IOException;

public class ListofBankRequestsController {
    @FXML
    private ListView clientList;
    @FXML
    private ListView messages;
    @FXML

    public void initialize() {
        showClients();
    }

    public void showMessages() {
        messages.getItems().add("");
        messages.getItems().add("Messages");
        for (int i=0; i<BankRepresentativeService.getClientsContor(LoginController.bankRepresentative); i++) {
            if (BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i] == clientList.getSelectionModel().getSelectedItem()) {
                for (int j=0; j<ClientService.getContorClient(); j++) {
                    messages.getItems().clear();
                    messages.getItems().add("");
                    messages.getItems().add("Messages");
                    for(int k=0; k<ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getContor(); k++) {

                        if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k] == null) {
                            messages.getItems();
                        } else {
                            messages.getItems().add(ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k]);
                        }
                    }
                }
            }
        }
        if (clientList.getSelectionModel().getSelectedItem().toString() == "" || clientList.getSelectionModel().getSelectedItem().toString() == "Client list") {
            messages.getItems().clear();
        }
    }

    public void showClients() {
        clientList.getItems().add("");
        clientList.getItems().add("Client list");
        for (int i = 0; i < BankRepresentativeService.getClientsContor(LoginController.bankRepresentative); i++) {
            if (BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i] == null) {
                clientList.getItems();
            } else {
                clientList.getItems().add(BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i]);
            }
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(BankRepresentativeController.class.getResource("/bankrepresentative.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Bank Representative Menu");
        window.show();

    }
}
