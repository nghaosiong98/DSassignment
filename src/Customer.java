public class Customer {
    private int arrival;
    private char status;
    private int ticket;
    private int waitTime;

    public Customer(int arrival,char status, int ticket){
        this.arrival = arrival;
        this.status = status;
        this.ticket = ticket;
    }

    public int getArrival() {
        return arrival;
    }

    public char getStatus() {
        return status;
    }

    public int getTicket() {
        return ticket;
    }


}
