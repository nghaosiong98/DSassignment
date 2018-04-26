import java.util.LinkedList;

public class Queue <Customer>{
    private LinkedList<Customer> q = new LinkedList<>();

    public void add(Customer c){
        q.addLast(c);
    }

    public boolean isEmpty(){
        return q.isEmpty();
    }

    public Customer dequeue(){
        return q.removeFirst();
    }

    public Customer getFirst(){
        return q.getFirst();
    }

    public int getSize(){
        return q.size();
    }


}
