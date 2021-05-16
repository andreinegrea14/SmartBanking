package smart.banking.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {
    public static final String ADMIN = "admin";

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

    @Test
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    void testUserIsAddedToDatabase() throws UsernameAlreadyExists {
        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }

    @Test
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExists.class, () -> {
            UserService.addUser(ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN);
        });
    }

    @Test
    void testCheckUserDoesNotAlreadyExist() throws UsernameAlreadyExists {
        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertThrows(UsernameAlreadyExists.class, () -> {
            UserService.checkUserDoesNotAlreadyExist(ADMIN);
        });
    }
}