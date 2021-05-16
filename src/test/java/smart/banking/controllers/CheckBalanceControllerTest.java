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
import smart.banking.exceptions.ClientAlreadyExist;
import smart.banking.model.Client;
import smart.banking.services.ClientService;
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class CheckBalanceControllerTest {
    private static final double FUNDS = 100;
    private static final String CORRECT_PASSWORD = "password";

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("checkBalance.fxml"));
        primaryStage.setTitle("Smart-Banking");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testOpeningSeeTransactions(FxRobot robot) {
        robot.clickOn("#seeTransactions");
    }

    @Test
    void testOpeningTransferFunds(FxRobot robot) {
        robot.clickOn("#transferFunds");
    }
    @Test
    void testOpeningRequestBankSupport(FxRobot robot) {
        robot.clickOn("#requestBankSupport");
    }

    @Test
    void testBackToLoginButtonFromCheckBalance(FxRobot robot) {
        robot.clickOn("#logout");
    }

    @Test
    void testAddingFunds(FxRobot robot) {
        Client client = new Client("Username", 0);
        robot.clickOn("#funds");
        robot.write(String.valueOf(FUNDS));
        robot.clickOn("#addFunds");
        client.setFunds(FUNDS);
        assertThat(client.getFunds()).isEqualTo(FUNDS);
    }
}