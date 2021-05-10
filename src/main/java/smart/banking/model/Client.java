package smart.banking.model;

import org.dizitart.no2.objects.Id;

public class Client {
    @Id
    private String username;
    private int funds;
    public Client() {

    }

    public void setFunds(int funds) {
        this.funds = funds;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client (String username ){
        this.username = username;
    }
    public Client(String username, int funds){
        this.username = username;
        this.funds = funds;
    }
    public void addFunds(int funds){
        this.funds = this.funds + funds;
    }

    public int getFunds(){
        return funds;
    }


    public String getUsername(){
        return username;
    }

}