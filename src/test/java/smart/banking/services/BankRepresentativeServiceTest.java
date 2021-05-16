package smart.banking.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import smart.banking.exceptions.BankRepresentativeAlreadyExists;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.BankRepresentative;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankRepresentativeServiceTest {
    public static final String ADMIN = "admin";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-smart-banking";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        BankRepresentativeService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        BankRepresentativeService.close();
    }

    @Test
    void testDatabaseIsInitializedAndNoBankRepresentativeIsPersisted() {
        assertThat(BankRepresentativeService.getAllBankRepresentatives()).isNotNull();
        assertThat(BankRepresentativeService.getAllBankRepresentatives()).isEmpty();
    }

    @Test
    void testBankRepresentativeIsAddedToDatabase() throws BankRepresentativeAlreadyExists {
        BankRepresentativeService.addBankRepresentative(ADMIN);
        assertThat(BankRepresentativeService.getAllBankRepresentatives()).isNotEmpty();
        assertThat(BankRepresentativeService.getAllBankRepresentatives()).size().isEqualTo(1);
        BankRepresentative bankRepresentative = BankRepresentativeService.getAllBankRepresentatives().get(0);
        assertThat(bankRepresentative).isNotNull();
        assertThat(bankRepresentative.getUsername()).isEqualTo(ADMIN);
    }

    @Test
    void testBankRepresentativeCanNotBeAddedTwice() {
        assertThrows(BankRepresentativeAlreadyExists.class, () -> {
            BankRepresentativeService.addBankRepresentative(ADMIN);
            BankRepresentativeService.addBankRepresentative(ADMIN);
        });
    }

    @Test
    void testCheckBankRepresentativeDoesNotAlreadyExist() throws BankRepresentativeAlreadyExists {
        BankRepresentativeService.addBankRepresentative(ADMIN);
        assertThrows(BankRepresentativeAlreadyExists.class, () -> {
            BankRepresentativeService.checkBankRepresentativeAlreadyExists(ADMIN);
        });
    }
}