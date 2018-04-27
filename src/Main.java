import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    final static int NUMBEROFCOUNTER = 4;

    static Queue NVIP = new Queue();
    static Queue VIP = new Queue();
    static int numberOfCustomers;
    static int customerCounter = 0;
    static int globalTime = 0;

    public static void checkStatus(Customer customer){
        if(customer.getStatus()=='N'){
            NVIP.add(customer);
        }else if(customer.getStatus()=='V'){
            VIP.add(customer);
        }
    }

    public static void display(Customer c, Counter counter){
        if(c.getStatus()=='N')
            System.out.printf("|%-10d|%-20d|%-15d|%-20d|%-15d|%-8s|%-8c\n",c.getArrival(),globalTime,counter.getEndTime(),counter.getProcessTime(),(globalTime-c.getArrival()),"Normal",counter.getLabel());
        else if(c.getStatus()=='V')
            System.out.printf("|%-10d|%-20d|%-15d|%-20d|%-15d|%-8s|%-8c\n",c.getArrival(),globalTime,counter.getEndTime(),counter.getProcessTime(),(globalTime-c.getArrival()),"VIP",counter.getLabel());
    }

    public static void sortCounter(Counter[] counters){
        for(int i=1;i<counters.length;i++){
            int j;
            Counter current = counters[i];
            for(j=i-1;j>=0 && current.getTimeRequired()<counters[j].getTimeRequired();j--){
                counters[j+1] = counters[j];
            }
            counters[j+1] = current;
        }
    }

    public static void getNumberOfCustomers() throws FileNotFoundException {
        Scanner input = new Scanner(new FileInputStream("input.txt"));
        numberOfCustomers = Integer.parseInt(input.nextLine());
    }

    public static boolean checkRemain(Counter[] counterArray){
        for(int i=0;i<counterArray.length;i++){
            if(counterArray[i].getAvailability()==false){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {
        Counter[] counterArray = new Counter[NUMBEROFCOUNTER];
        counterArray[0] = new Counter('A',10);
        counterArray[1] = new Counter('B',15);
        counterArray[2] = new Counter('C',30);
        counterArray[3] = new Counter('D',15);
        sortCounter(counterArray);

        Scanner keyboard = new Scanner(System.in);
        Scanner input = new Scanner(new FileInputStream("input.txt"));
        PrintWriter output = new PrintWriter(new FileOutputStream("input.txt"));

        System.out.println("Enter number of customer: ");
        numberOfCustomers = keyboard.nextInt();
        output.println(numberOfCustomers);
        for(int i=0;i<numberOfCustomers;i++){
            int tempI = keyboard.nextInt();
            String temp = keyboard.next();
            int tempII = keyboard.nextInt();
            if(temp.equals("#")){
                break;
            }
            output.println(tempI + " " +  temp + " " + tempII);
        }
        output.close();

        getNumberOfCustomers(); //get number of customer
        numberOfCustomers = input.nextInt();
        System.out.printf("|%-10s|%-20s|%-15s|%-20s|%-15s|%-8s|%-8s|\n","Arrival","Start Processing","End Processing","Processing Time","Waiting Time", "Queue", "Counter");



        while(input.hasNext()){
            int arriveTime = input.nextInt(); //get arrive time
            char status = input.next().charAt(0); //get status
            int numberOfTicket = input.nextInt(); //get num of tickets
            Customer customer = new Customer(arriveTime,status,numberOfTicket); //create customer object
            checkStatus(customer); //here customer will be assign to 2 queues according to their status
        }

        while(!NVIP.isEmpty()||!VIP.isEmpty()||checkRemain(counterArray)){
            /*Below is the code to check whether the counter done processing a customer*/
            for(int i=0;i<counterArray.length;i++){
                if(counterArray[i].getAvailability()==false){
                    if(counterArray[i].getEndTime()==globalTime){
                        counterArray[i].remove();
                    }
                }
            }

            /*----------------------------------------------------------------------------------------*
            /*Below is the code assigning customer to counter*/
            for(int j=0;j<counterArray.length;j++){
                if(counterArray[j].getAvailability()==true){ //check for counter availability
                    //check VIP queue first, then NVIP
                    if(!VIP.isEmpty()){
                        Customer c = (Customer) VIP.getFirst();
                        if(c.getArrival()<=globalTime){  //if time ngam then assign the VIP to the counter
                            counterArray[j].add((Customer) VIP.dequeue()); //availability = false
                            customerCounter++;
                            counterArray[j].setProcessTime(c.getTicket());
                            counterArray[j].setEndTime(globalTime);
                            display(c,counterArray[j]);
                            continue;
                        }
                    }
                    if(!NVIP.isEmpty()){
                        Customer c = (Customer) NVIP.getFirst();
                        if(c.getArrival()<=globalTime){  //if time ngam then assign the NVIP to the counter
                            counterArray[j].add((Customer) NVIP.dequeue()); //availability = false
                            customerCounter++;
                            counterArray[j].setProcessTime(c.getTicket());
                            counterArray[j].setEndTime(globalTime);
                            display(c,counterArray[j]);
                            continue;
                        }
                    }
                }
            }
            globalTime++;
        }

        System.out.println("Total completion time: " + (globalTime-1));
    }

}
