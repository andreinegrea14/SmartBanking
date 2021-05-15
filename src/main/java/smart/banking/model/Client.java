package smart.banking.model;

import org.dizitart.no2.objects.Id;

public class Client {
    @Id
    private String username;
    private double funds;
    private String[] transaction = new String[100];
    private String[] userTransaction = new String[100];
    private int[] rs = new int[100];
    private int[] status = new int[100];
    private int transactions = 0;
    private int userTransactionContor = 0;
    private int receivedOrSentContor = 0;
    private int contor = 0;
    private int statusContor = 0;
    private String[] messages = new String[100];
    private String[] clients = new String[100];
    private int contorClients = 0;
    private String[] reviews = new String[100];
    private int reviewsContor = 0;
    private String[] statusReviews = new String[100];
    private int statusReviewsContor = 0;
    public void setContor(int contor) {
        this.contor = contor;
    }

    public void sendReview(String review, int index) {
        if(reviewsContor == reviews.length){
            String[] aux = new String[reviews.length + 10];
            for(int i=0; i < reviews.length; i++){
                aux[i]=reviews[i];
            }
            reviews=aux;

        }
        reviewsContor++;
        this.reviews[index] = review;
    }
    public int getReviewsContor() {
        return reviewsContor;
    }
    public int getStatusReviewsContor() {
        return statusReviewsContor;
    }
    public void sendReviewStatus(String status, int index) {
        if(statusReviewsContor == statusReviews.length){
            String[] aux = new String[statusReviews.length + 10];
            for(int i=0; i < statusReviews.length; i++){
                aux[i]=statusReviews[i];
            }
            statusReviews=aux;

        }
        statusReviewsContor++;
        this.statusReviews[index] = status;
    }

    public int getContorClients() {
        return contorClients;
    }
    public void setContorClients(int contorClients) {
        this.contorClients = contorClients;
    }
    public void setClients(String[] clienti) {
        this.clients = clienti;
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

    public void setStatus(int[] status) {
        this.status = status;
    }

    public void setStatusContor(int statusContor) {
        this.statusContor = statusContor;
    }
    public int getStatusContor() {
        return statusContor;
    }
    public String[] getStatusReview() {
        return statusReviews;
    }
    public String[] getReviews() {
        return reviews;
    }
    public void sendStatus(int statusProvided) {
        if(statusContor == status.length){
            int[] aux = new int[status.length + 10];
            for(int i=0; i < status.length; i++){
                aux[i]=status[i];
            }
            status=aux;

        }
        this.status[statusContor++] = statusProvided;
    }
    public int[] getStatus() {
        return status;
    }
    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public int getContor() {
        return contor;
    }
    public String[] getMessages() throws ArrayIndexOutOfBoundsException {
        try {
            return messages;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public void sendMessage(String message1) {
        if(contor == messages.length){
            String[] aux = new String[messages.length + 10];
            for(int i=0; i < messages.length; i++){
                aux[i]=messages[i];
            }
            messages=aux;

        }
        this.messages[contor++] = message1;
    }

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