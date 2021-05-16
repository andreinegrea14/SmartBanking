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
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import smart.banking.exceptions.ClientAlreadyExist;
import smart.banking.model.Client;
import smart.banking.services.ClientService;
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class TransferFundsControllerTest {

    private static final String CORRECT_USERNAME = "username55";
    private static final String INCORRECT_USERNAME = "username59";
    private static final String CORRECT_CURRENCY = "EURO";
    private static final String INCORRECT_CURRENCY = "Franc";
    private static final double INCORRECT_FUNDS = 102;
    private static final double CORRECT_FUNDS = 1020;



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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("transferFunds.fxml"));
        primaryStage.setTitle("Smart-Banking");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    void testOpeningSeeTransactions(FxRobot robot) {
        robot.clickOn("#seeTransactions");
    }

    @Test
    void testOpeningCheckBalance(FxRobot robot) {
        robot.clickOn("#checkBalance");
    }
    @Test
    void testOpeningRequestBankSupport(FxRobot robot) {
        robot.clickOn("#requestBankSupport");
    }

    @Test
    void testLogout(FxRobot robot) {
        robot.clickOn("#logout");
    }

    @Test
    void testEmptyFields(FxRobot robot) {
        robot.clickOn("#send");
        Assertions.assertThat(robot.lookup("#verifyFundsMessage").queryText()).hasText("Insuficient funds!");
    }

    @Test
    void testEmptyFundsField(FxRobot robot) throws ClientAlreadyExist {
        ClientService.addClient(CORRECT_USERNAME, 0);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#send");
        Assertions.assertThat(robot.lookup("#verifyFundsMessage").queryText()).hasText("Insuficient funds!");
    }
}