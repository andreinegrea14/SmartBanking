package smart.banking.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smart.banking.exceptions.ClientAlreadyExist;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.Client;
import smart.banking.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientServiceTest {
    public static final String ADMIN = "admin";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-smart-banking";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ClientService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        ClientService.close();
    }

    @Test
    void testDatabaseIsInitializedAndNoClientIsPersisted() {
        assertThat(ClientService.getAllClients()).isNotNull();
        assertThat(ClientService.getAllClients()).isEmpty();
    }

    @Test
    void testClientIsAddedToDatabase() throws ClientAlreadyExist {
        ClientService.addClient(ADMIN, 0);
        assertThat(ClientService.getAllClients()).isNotEmpty();
        assertThat(ClientService.getAllClients()).size().isEqualTo(1);
        Client client = ClientService.getAllClients().get(0);
        assertThat(client).isNotNull();
        assertThat(client.getUsername()).isEqualTo(ADMIN);
    }

    @Test
    void testClientCanNotBeAddedTwice() throws ClientAlreadyExist {
        assertThrows(ClientAlreadyExist.class, () -> {
            ClientService.addClient(ADMIN, 0);
            ClientService.addClient(ADMIN, 0);
        });
    }

    @Test
    void testCheckClientDoesNotAlreadyExist() throws ClientAlreadyExist {
        ClientService.addClient(ADMIN, 0);
        assertThrows(ClientAlreadyExist.class, () -> {
            ClientService.checkClientAlreadyExists(ADMIN);
        });
    }
}