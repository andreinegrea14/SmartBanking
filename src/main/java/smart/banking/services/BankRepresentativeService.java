package smart.banking.services;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import smart.banking.exceptions.BankRepresentativeAlreadyExists;
import smart.banking.exceptions.ClientAlreadyExist;
import smart.banking.model.BankRepresentative;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import smart.banking.model.Client;

import java.util.List;
import java.util.Objects;
import static smart.banking.services.FileSystemService.getPathToFile3;
public class BankRepresentativeService {

    private static ObjectRepository<BankRepresentative> bankRepresentativeObjectRepository;
    private static Nitrite database;
    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile3("bankrepresentativesDatabase.db").toFile())
                .openOrCreate("test", "test");

        bankRepresentativeObjectRepository = database.getRepository(BankRepresentative.class);
    }

    public static void addBankRepresentative(String username) throws BankRepresentativeAlreadyExists {
        checkBankRepresentativeAlreadyExists(username);
        bankRepresentativeObjectRepository.insert(new BankRepresentative(username));
    }

    public static BankRepresentative getBankRepresentative(String username){
        for (BankRepresentative bankrepresentative : bankRepresentativeObjectRepository.find())
            if (Objects.equals(username, bankrepresentative.getUsername()) ){
                return bankrepresentative;
            }
        return new BankRepresentative("Username");

    }
    public static void addClient(String username) {
        for (BankRepresentative bankRepresentative : bankRepresentativeObjectRepository.find()) {
            bankRepresentative.addClients(username);
            bankRepresentativeObjectRepository.update(bankRepresentative);
        }
    }
    public static int getClientsContor(String username) {
        for (BankRepresentative bankRepresentative : bankRepresentativeObjectRepository.find()) {
            if (username.equals(bankRepresentative.getUsername())) {
                return bankRepresentative.getContorClients();
            }
        }
        return 0;
    }
    public static int getContor(String username) {
        for (BankRepresentative bankRepresentative : bankRepresentativeObjectRepository.find()) {
            if (username.equals(bankRepresentative.getUsername())) {
                return bankRepresentative.getContorClients();
            }
        }
        return 0;
    }
    public static void close() {
        bankRepresentativeObjectRepository.close();
        database.close();
    }
    public static List<BankRepresentative> getAllBankRepresentatives() {
        return bankRepresentativeObjectRepository.find().toList();
    }

    public static void checkBankRepresentativeAlreadyExists(String username) throws BankRepresentativeAlreadyExists {
        for (BankRepresentative bankRepresentative : bankRepresentativeObjectRepository.find()) {
            if (Objects.equals(username, bankRepresentative.getUsername()))
                throw new BankRepresentativeAlreadyExists(username);
        }
    }
}