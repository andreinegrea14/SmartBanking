package smart.banking.exceptions;

public class ClientAlreadyExist extends  Exception{
    private String username;

    public ClientAlreadyExist(String username) {
        super(String.format("A client with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
