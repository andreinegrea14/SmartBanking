package smart.banking.model;

import org.dizitart.no2.objects.Id;

public class Client {
    @Id
    private String username;
    private double funds;
    public Client() {

    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client (String username ){
        this.username = username;
    }
    public Client(String username, double funds){
        this.username = username;
        this.funds = funds;
    }
    public void addFunds(double funds){
        this.funds = this.funds + funds;
    }

    public double getFunds(){
        return funds;
    }


    public String getUsername(){
        return username;
    }

}