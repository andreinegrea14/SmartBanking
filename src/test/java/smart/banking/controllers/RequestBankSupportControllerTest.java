package smart.banking.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import smart.banking.services.ClientService;
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;

import static org.junit.jupiter.api.Assertions.*;


    @ExtendWith(ApplicationExtension.class)
    class RequestBankSupportControllerTest  {
        private static final String MESSAGE = "message";

        @BeforeEach
        void setUp() throws Exception {
            FileSystemService.APPLICATION_FOLDER = ".test-smart-banking";
            FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
            UserService.initDatabase();
            ClientService.initDatabase();
        }

        @AfterEach
        void tearDown() {
            UserService.close();
            ClientService.close();
        }

        @Start
        void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("requestSupport.fxml"));
            primaryStage.setTitle("Smart-Banking");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        @Test
        void testOpeningSeeTransactions(FxRobot robot) {
            robot.clickOn("#seeTransactions");
        }

        @Test
        void testOpeningSeeTransferFunds(FxRobot robot) {
            robot.clickOn("#transferFunds");
        }
        @Test
        void testOpeningCheckBalance(FxRobot robot) {
            robot.clickOn("#checkBalance");
        }

        @Test
        void testBackToLoginButtonFromRequestBankSupport(FxRobot robot) {
            robot.clickOn("#logout");
        }

        @Test
        void testOpeningSendRequest(FxRobot robot) {
            robot.clickOn("#sendRequest");
        }

        @Test
        void testOpeningSeeAllRequests(FxRobot robot) {
            robot.clickOn("#SeeAllRequests");
        }
}