import java.util.ArrayList;

public class Counter <E> {
    private int timeRequired; //time of the counter to process a ticket
    private int processTime; //timeRequired to process n ticket(s)
    private boolean availability; //counter availability
    private char label; //counter label
    private int endTime; //time the moment the counter done process n ticket(s)
    private ArrayList<Customer> slot = new ArrayList<>(); //a holder to hold customer at the counter

    public Counter(char label, int timeRequired){
        this.label = label;
        availability = true;
        processTime = 0;
        endTime = 0;
        this.timeRequired=timeRequired;
    }

    public void add(Customer c){
        slot.add(c);
        availability = false;
    }

    public void remove(){
        slot.remove(0);
        this.availability = true;
        this.endTime = 0;
        this.processTime = 0;
    }

    public void setProcessTime(int n){
        processTime = n * timeRequired;
    }

//    public void resetTime(){
//        this.processTime = 0;
//        this.endTime = 0;
//        this.availability = true;
//    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void setEndTime(){
        if(!slot.isEmpty()){
            int arrival = slot.get(0).getArrival();
            endTime = arrival + getProcessTime();
        }
    }

    public int getEndTime(){
        return endTime;
    }

    public char getLabel(){
        return this.label;
    }
}
