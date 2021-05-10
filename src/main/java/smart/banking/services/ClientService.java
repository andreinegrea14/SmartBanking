package smart.banking.services;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import smart.banking.model.Client;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import java.util.Objects;
import static smart.banking.services.FileSystemService.getPathToFile2;

public class ClientService {

    private static ObjectRepository<Client> clientObjectRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile2("clienti.db").toFile())
                .openOrCreate("test", "test");

        clientObjectRepository = database.getRepository(Client.class);
    }

    public static void addClient(String username, int funds) throws UniqueConstraintException {
        try {
            clientObjectRepository.insert(new Client(username, funds));
        } catch(UniqueConstraintException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addFunds(String username, int funds) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.addFunds(funds);
                clientObjectRepository.update(client);
            }
        }
    }

    public static int getFunds(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return client.getFunds();
            }
        }
        return 0;

    }
    public static Client getClient(String username){
        for (Client client : clientObjectRepository.find())
            if (Objects.equals(username, client.getUsername()) ){
                return client;
            }
        return new Client("Username");

    }
}