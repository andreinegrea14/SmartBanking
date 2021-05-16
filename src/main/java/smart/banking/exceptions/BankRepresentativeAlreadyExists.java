package smart.banking.exceptions;

public class BankRepresentativeAlreadyExists extends Exception{
    private String username;

    public BankRepresentativeAlreadyExists(String username) {
        super(String.format("A bank representative with the username %s already exists!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
