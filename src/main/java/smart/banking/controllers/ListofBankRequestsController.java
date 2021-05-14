package smart.banking.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private ListView statusList;
    @FXML
    private ListView reviewList;
    @FXML
    Button button = new Button(">>");
    @FXML
    public static int requestIndex;
    @FXML
    public static String clientName;
    @FXML


    public void initialize() {
        showClients();
    }
    int l;
    public void showButton() {
        int l = messages.getSelectionModel().getSelectedIndex() - 2;
        if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l] == null) {
            reviewList.getItems().clear();
            reviewList.getItems().add("");
            reviewList.getItems().add("Review");
            reviewList.getItems().add(button);
        } else {
            reviewList.getItems().clear();
        }

        button.setOnAction((event)  -> {
            try {
                ListofBankRequestsController.clientName = clientList.getSelectionModel().getSelectedItem().toString();
                ListofBankRequestsController.requestIndex = messages.getSelectionModel().getSelectedIndex() - 2;
                openReviewScreen(event);
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }
    public void showMessagesStatusesAndReviews() {
        messages.getItems().add("");
        messages.getItems().add("Messages");
        statusList.getItems().add("");
        statusList.getItems().add("Status");

        for (int i=0; i<BankRepresentativeService.getClientsContor(LoginController.bankRepresentative); i++) {
            if (BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i] == clientList.getSelectionModel().getSelectedItem()) {
                for (int j=0; j<ClientService.getContorClient(); j++) {
                    messages.getItems().clear();
                    messages.getItems().add("");
                    messages.getItems().add("Messages");
                    statusList.getItems().clear();
                    statusList.getItems().add("");
                    statusList.getItems().add("Status");
                    reviewList.getItems().clear();
                    reviewList.getItems().add("");
                    reviewList.getItems().add("Review");
                    for(int k=0; k<ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getContor(); k++) {

                        if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k] == null) {
                            messages.getItems();
                        } else {
                            messages.getItems().add(ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k]);
                        }
                    }
                    for (l = 0; l < ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusContor(); l++) {
                        if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l] == null) {
                            statusList.getItems().add("UNREVIEWED");
                        } else if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l].equals("Accepted")) {
                            statusList.getItems().add("REVIEWED");
                        } else if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l].equals("Rejected")) {
                            statusList.getItems().add("REVIEWED");
                        } else {
                            statusList.getItems().add("UNREVIEWED");
                        }
                    }
                }
            }
        }
        if (clientList.getSelectionModel().getSelectedItem().toString() == "" || clientList.getSelectionModel().getSelectedItem().toString() == "Client list") {
            messages.getItems().clear();
            statusList.getItems().clear();
            reviewList.getItems().clear();
        }
    }


    public void openReviewScreen(ActionEvent event)throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/sendReview.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window =  ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Send review");
        window.show();

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
