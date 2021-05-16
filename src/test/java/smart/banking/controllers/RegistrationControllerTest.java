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
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;


@ExtendWith(ApplicationExtension.class)
class RegistrationControllerTest {

    private static final String CORRECT_USERNAME = "username";
    private static final String CORRECT_PASSWORD = "password";
    private static final String CLIENT_ROLE = "Client";
    private static final String BANK_REPRESENTATIVE_ROLE = "Bank Representative";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-smart-banking";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Smart-Banking");
        primaryStage.setScene(new Scene(root, 600, 525));
        primaryStage.show();
    }

    @Test
    void testEmptyUsernameField(FxRobot robot) {
        robot.clickOn("#register");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Please introduce an username!");
    }

    @Test
    void testSuccessfulRegistrationForClient(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(CLIENT_ROLE);
        robot.clickOn("#register");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(CORRECT_USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(CORRECT_USERNAME, CORRECT_PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(CLIENT_ROLE);
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");
    }

    @Test
    void testSuccesfulRegistrationForBankRepresentative(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(BANK_REPRESENTATIVE_ROLE);
        robot.clickOn("#register");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(CORRECT_USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(CORRECT_USERNAME, CORRECT_PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(BANK_REPRESENTATIVE_ROLE);
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Account created successfully!");

    }

    @Test

    void testRegistrationAccountAlreadyExists(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#role");
        robot.clickOn(CLIENT_ROLE);
        robot.clickOn("#register");
        robot.clickOn("#register");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(String.format("An account with the username %s already exists!", CORRECT_USERNAME));
    }

    @Test
    void testBackToLoginButtonFromRegistration(FxRobot robot) {
        robot.clickOn("#goBack");
    }
}