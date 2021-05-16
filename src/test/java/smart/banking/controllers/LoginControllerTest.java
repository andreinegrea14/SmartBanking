package smart.banking.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.User;
import smart.banking.services.FileSystemService;
import smart.banking.services.UserService;

import static org.assertj.core.api.InstanceOfAssertFactories.atomicIntegerFieldUpdater;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {

    private static final String CORRECT_USERNAME = "username55";
    private static final String CORRECT_PASSWORD = "password";
    private static final String INCORRECT_USERNAME = "password66";
    private static final String INCORRECT_PASSWORD = "password67";
    private static final String CORRECT_ROLE = "Client";

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Smart-Banking");
        primaryStage.setScene(new Scene(root, 600, 525));
        primaryStage.show();
    }

    @Test

    void testEmptyUsernameField(FxRobot robot) {
        robot.clickOn("#login");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Please introduce an username!");
    }

    @Test

    void testEmptyPasswordField(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#login");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Please introduce a password!");
    }

    @Test
    void testInvalidCredentials(FxRobot robot) throws UsernameAlreadyExists {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, "Test");
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#login");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Incorrect login! Please try again.");
    }

    @Test
    void testInvalidPassword(FxRobot robot) throws UsernameAlreadyExists {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, CORRECT_ROLE);
        robot.clickOn("#username");
        robot.write(CORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(INCORRECT_PASSWORD);
        robot.clickOn("#login");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Incorrect login! Please try again.");
    }

    @Test
    void testInvalidUsername(FxRobot robot) throws UsernameAlreadyExists {
        UserService.addUser(CORRECT_USERNAME, CORRECT_PASSWORD, CORRECT_ROLE);
        robot.clickOn("#username");
        robot.write(INCORRECT_USERNAME);
        robot.clickOn("#password");
        robot.write(CORRECT_PASSWORD);
        robot.clickOn("#login");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Incorrect login! Please try again.");
    }
}