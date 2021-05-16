package smart.banking.services;
import org.dizitart.no2.exceptions.UniqueConstraintException;
import smart.banking.exceptions.ClientAlreadyExist;
import smart.banking.exceptions.UsernameAlreadyExists;
import smart.banking.model.BankRepresentative;
import smart.banking.model.Client;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import smart.banking.model.User;

import java.util.List;
import java.util.Objects;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import static smart.banking.services.FileSystemService.getPathToFile2;

public class ClientService {

    private static ObjectRepository<Client> clientObjectRepository;
    private static DecimalFormat formatTwoDecimals = new DecimalFormat("#.##");
    private static Nitrite database;
    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile2("clientsDatabase.db").toFile())
                .openOrCreate("test", "test");

        clientObjectRepository = database.getRepository(Client.class);
    }

    public static void addClient(String username, double funds) throws ClientAlreadyExist {
        checkClientAlreadyExists(username);
            clientObjectRepository.insert(new Client(username, funds));
    }

    public static int getContorClient() {
        for (Client client : clientObjectRepository.find()) {
            return client.getContorClients();
        }
        return 0;

    }

    public static void addClient(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.addClients(username);
                clientObjectRepository.update(client);
            }
        }
    }

    public static void addFunds(String username, double funds) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.addFunds(funds);
                clientObjectRepository.update(client);
            }
        }
    }

    public static void extractFunds(String username, double funds) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.extractFunds(funds);
                clientObjectRepository.update(client);
            }
        }
    }

    public static double getFunds(String username) {
        formatTwoDecimals.setRoundingMode(RoundingMode.DOWN);
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return Double.parseDouble(formatTwoDecimals.format(client.getFunds()));
            }
        }
        return 0;
    }

    public static void sendMessage(String username, String message) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.sendMessage(message);
                client.sendStatus(0);
                clientObjectRepository.update(client);
            }
        }
    }
    public static void sendReviews(String clientName, String review, String status, int index) {
        for (Client client : clientObjectRepository.find()) {
            if (clientName.equals(client.getUsername())) {
                client.sendReview(review, index);
                client.sendReviewStatus(status, index);
                clientObjectRepository.update(client);
            }
        }
    }

    public static int getContor(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return client.getContor();
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
    public static void addTransaction(String username, double funds, String otherClient, int status) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                client.addTransaction(funds);
                client.addUserTransaction(otherClient);
                client.sendReceivedOrSend(status);
                clientObjectRepository.update(client);
            }
        }
    }
    public static int getTransactionsContor(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return client.getTransactionsContor();
            }
        }
        return 0;
    }
    public static int getUserTransactionContor(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return client.getUserTransactionContor();
            }
        }
        return 0;

    }
    public static int getSendOrReceivedContor(String username) {
        for (Client client : clientObjectRepository.find()) {
            if (username.equals(client.getUsername())) {
                return client.getReceivedOrSentContor();
            }
        }
        return 0;
    }
    public static void close() {
        clientObjectRepository.close();
        database.close();
    }
    public static List<Client> getAllClients() {
        return clientObjectRepository.find().toList();
    }

    public static void checkClientAlreadyExists(String username) throws ClientAlreadyExist {
        for (Client client : clientObjectRepository.find()) {
            if (Objects.equals(username, client.getUsername()))
                throw new ClientAlreadyExist(username);
        }
    }
}