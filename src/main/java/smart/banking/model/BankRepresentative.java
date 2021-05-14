package smart.banking.model;
import java.lang.String;
import org.dizitart.no2.objects.Id;

public class BankRepresentative {

    @Id
    private String username;
    private String feedback;
    private String[] clients = new String[100];
    private int contorClients = 0;
    public BankRepresentative() {

    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public int getContorClients()
    {
        return contorClients;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String[] getClients() {
        return clients;
    }
    public void addClients(String username) {
        if(contorClients == clients.length){
            String[] aux = new String[clients.length + 10];
            for(int i=0; i < clients.length; i++){
                aux[i]=clients[i];
            }
            clients=aux;

        }
        this.clients[contorClients++] = username;
    }


    public BankRepresentative (String username ){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void sendFeedback(String feedback1){
        feedback = feedback1;
    }
    public String getFeedback(){
        return feedback;
    }

}
