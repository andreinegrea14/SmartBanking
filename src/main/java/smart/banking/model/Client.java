package smart.banking.model;

import org.dizitart.no2.objects.Id;

public class Client {
    @Id
    private String username;
    private double funds;
    private String[] transaction = new String[100];
    private String[] userTransaction = new String[100];
    private int[] rs = new int[100];
    private int transactions = 0;
    private int userTransactionContor = 0;
    private int receivedOrSentContor = 0;
    public Client() {

    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }
    public int getTransactions(){
        return transactions;
    }

    public String[] getTransaction(){
        return transaction;
    }

    public void setTransaction(String[] transaction) {
        this.transaction = transaction;
    }
    public int getUserTransactionContor() {
        return userTransactionContor;
    }
    public int getTransactionsContor() {
        return transactions;
    }
    public void setReceivedOrSent(int[] receivedOrSent) {
        this.rs = receivedOrSent;
    }

    public void setUserTransaction(String[] userTransaction) {
        this.userTransaction = userTransaction;
    }

    public int[] getReceivedOrSent() {
        return rs;
    }
    public String[] getUserTransaction() {
        return userTransaction;
    }

    public void addTransaction(double funds) {
        if(transactions == transaction.length){
            String[] aux = new String[transaction.length + 10];
            for(int i=0; i < transaction.length; i++){
                aux[i]=transaction[i];
            }
            transaction=aux;

        }
        this.transaction[transactions++] = String.valueOf(funds);
    }

    public void addUserTransaction(String username) {
        if(userTransactionContor == userTransaction.length){
            String[] aux = new String[userTransaction.length + 10];
            for(int i=0; i < userTransaction.length; i++){
                aux[i]=userTransaction[i];
            }
            userTransaction=aux;

        }
        this.userTransaction[userTransactionContor++] = username;
    }
    public void sendReceivedOrSend(int statusProvided) {
        if(receivedOrSentContor == rs.length){
            int[] aux = new int[rs.length + 10];
            for(int i=0; i < rs.length; i++){
                aux[i]=rs[i];
            }
            rs=aux;

        }
        this.rs[receivedOrSentContor++] = statusProvided;
    }
    public int getReceivedOrSentContor() {
        return receivedOrSentContor;
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
    public void extractFunds(double funds){
        this.funds = this.funds - funds;
    }

    public double getFunds(){
        return funds;
    }


    public String getUsername(){
        return username;
    }

}