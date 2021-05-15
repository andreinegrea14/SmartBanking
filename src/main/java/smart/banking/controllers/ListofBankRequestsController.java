package smart.banking.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
    Button button = new Button("Add review");
    @FXML
    public static int requestIndex;
    @FXML
    public static String clientName;
    private String clientString;
    private String messageS;
    private String reviewS;
    private String statusS;



    public void initialize() {
        showClients();
    }

    public void showButton() {
        int l = messages.getSelectionModel().getSelectedIndex() - 1;
        if (l >= 0) {
            try {
                if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l] == null) {
                    reviewList.getItems().clear();
                    reviewS = "Review";
                    Text text2 = new Text(30,80, reviewS);
                    Font newFont2 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
                    text2.setFill(Color.valueOf("#000000"));
                    text2.setFont(newFont2);
                    reviewList.getItems().add(text2);
                    button.setMinSize(110, 15);
                    reviewList.getItems().add(button);
                } else {
                    reviewList.getItems().clear();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }


        button.setOnAction((event) -> {
            try {
                ListofBankRequestsController.clientName = clientList.getSelectionModel().getSelectedItem().toString();
                ListofBankRequestsController.requestIndex = messages.getSelectionModel().getSelectedIndex() - 1;
                openReviewScreen(event);
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    public void showMessagesStatusesAndReviews() {
            messageS= "Messages";
            statusS = "Status";
            reviewS = "Review";

            Text text = new Text(30,80,messageS);
            Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
            text.setFill(Color.valueOf("#000000"));
            text.setFont(newFont);
            Text text1 = new Text(30,80, statusS);
            Font newFont1 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
            text1.setFill(Color.valueOf("#000000"));
            text1.setFont(newFont1);
            Text text2 = new Text(30,80, reviewS);
            Font newFont2 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
            text2.setFill(Color.valueOf("#000000"));
            text2.setFont(newFont2);
            messages.getItems().clear();
            statusList.getItems().clear();
            reviewList.getItems().clear();
            messages.getItems().add(text);
            statusList.getItems().add(text1);
            reviewList.getItems().add(text2);

                for (int i = 0; i < BankRepresentativeService.getClientsContor(LoginController.bankRepresentative); i++) {
                    if (BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i] == clientList.getSelectionModel().getSelectedItem()) {
                        for (int j = 0; j < ClientService.getContorClient(); j++) {
                            messages.getItems().clear();
                            messages.getItems().add(text);
                            statusList.getItems().clear();
                            statusList.getItems().add(text1);
                            reviewList.getItems().clear();
                            reviewList.getItems().add(text2);
                            for (int k = 0; k < ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getContor(); k++) {
                                if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k] == null || ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k].isEmpty()) {
                                    messages.getItems().add("Empty request");
                                } else {
                                    messages.getItems().add(ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getMessages()[k]);
                                }
                            }
                            for (int l = 0; l < ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusContor(); l++) {
                                if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l] == null) {
                                    String statuses = "UNREVIEWED";
                                    Text text3 = new Text(30,80, statuses);
                                    Font newFont3 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
                                    text3.setFill(Color.valueOf("#CB4335"));
                                    text3.setFont(newFont3);
                                    statusList.getItems().add(text3);
                                } else if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l].equals("Accepted")) {
                                    String statuses = "REVIEWED";
                                    Text text3 = new Text(30,80, statuses);
                                    Font newFont3 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
                                    text3.setFill(Color.valueOf("#27AE60"));
                                    text3.setFont(newFont3);
                                    statusList.getItems().add(text3);
                                } else if (ClientService.getClient(clientList.getSelectionModel().getSelectedItem().toString()).getStatusReview()[l].equals("Rejected")) {
                                    String statuses = "REVIEWED";
                                    Text text3 = new Text(30,80, statuses);
                                    Font newFont3 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
                                    text3.setFill(Color.valueOf("#27AE60"));
                                    text3.setFont(newFont3);
                                    statusList.getItems().add(text3);
                                } else {
                                    String statuses = "UNREVIEWED";
                                    Text text3 = new Text(30,80, statuses);
                                    Font newFont3 = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
                                    text3.setFill(Color.valueOf("#27AE60"));
                                    text3.setFont(newFont3);
                                    statusList.getItems().add(text3);

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


    public void openReviewScreen(ActionEvent event) throws IOException {
        Parent adminWindow = FXMLLoader.load(ClientController.class.getResource("/sendReview.fxml"));
        Scene adminScene = new Scene(adminWindow);
        Stage window = ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(adminScene);
        window.setTitle("Send review");
        window.show();

    }

    public void showClients() {
        clientString = "Requests list";
        Text text = new Text(30,80, clientString);
        Font newFont = Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,14);
        text.setFill(Color.valueOf("#000000"));
        text.setFont(newFont);
        clientList.getItems().add(text);
        for (int i = 0; i < BankRepresentativeService.getClientsContor(LoginController.bankRepresentative); i++) {
            if (BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i] == null) {
                clientList.getItems();
            } else {

                clientList.getItems().add(BankRepresentativeService.getBankRepresentative(LoginController.bankRepresentative).getClients()[i]);
            }
        }
    }

    public void handleCloseButtonAction(ActionEvent event) throws IOException {
        Parent loginWindow = FXMLLoader.load(CheckBalanceController.class.getResource("/login.fxml"));
        Scene loginScene = new Scene(loginWindow, 600, 525);
        Stage window = ((Stage) (((Node) event.getSource()).getScene().getWindow()));
        window.setScene(loginScene);
        window.setTitle("Login");
        window.show();
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
