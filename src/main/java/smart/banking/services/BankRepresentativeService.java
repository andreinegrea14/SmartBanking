package smart.banking.services;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import smart.banking.model.BankRepresentative;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import java.util.Objects;
import static smart.banking.services.FileSystemService.getPathToFile3;
public class BankRepresentativeService {

    private static ObjectRepository<BankRepresentative> bankRepresentativeObjectRepository;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile3("bankrepresentatives.db").toFile())
                .openOrCreate("test", "test");

        bankRepresentativeObjectRepository = database.getRepository(BankRepresentative.class);
    }

    public static void addBankRepresentative(String username) throws UniqueConstraintException {
        try {
            bankRepresentativeObjectRepository.insert(new BankRepresentative(username));
        } catch(UniqueConstraintException e) {
            System.out.println(e.getMessage());
        }
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
}