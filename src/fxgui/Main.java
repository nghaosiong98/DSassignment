/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxgui;

/**
 *
 * @author End User
 */
import java.io.*;
import java.util.ArrayList;

public class Main {
    final static int NUMBEROFCOUNTER = 4;
    static int Nprice,Vprice;
    int profit =0;
    static Queue NVIP = new Queue();
    static Queue VIP = new Queue();
    int numberOfCustomers;
    int customerCounter = 0;
    int globalTime = 0;
    ArrayList<String> results = new ArrayList<>();
    
    public Main(int numberOfCustomers, Queue normalQueue, Queue vipQueue,int Nprice,int Vprice){
        this.numberOfCustomers = numberOfCustomers;
        this.Nprice=Nprice;
        this.Vprice=Vprice;
        this.NVIP = normalQueue;
        VIP = vipQueue;
    }

    public void display(Customer c, Counter counter){
        if(c.getStatus()=='N'){
            String result = String.format("|%-10d |%-20d\t     |%-15d\t       |%-20d\t    |%-15d\t       |%-8s\t   |%-8c",c.getArrival(),globalTime,counter.getEndTime(),counter.getProcessTime(),(globalTime-c.getArrival()),"Normal",counter.getLabel());
            System.out.printf(result);
            results.add(result);
        }
            
        else if(c.getStatus()=='V'){
            String result = String.format("|%-10d |%-20d\t     |%-15d\t       |%-20d\t    |%-15d\t       |%-8s\t   |%-8c",c.getArrival(),globalTime,counter.getEndTime(),counter.getProcessTime(),(globalTime-c.getArrival()),"VIP",counter.getLabel());
            System.out.println(result);
            results.add(result);
        }
            
    }

    public void sortCounter(Counter[] counters){
        for(int i=1;i<counters.length;i++){
            int j;
            Counter current = counters[i];
            for(j=i-1;j>=0 && current.getTimeRequired()<counters[j].getTimeRequired();j--){
                counters[j+1] = counters[j];
            }
            counters[j+1] = current;
        }
    }

    public boolean checkRemain(Counter[] counterArray){
        for(int i=0;i<counterArray.length;i++){
            if(counterArray[i].getAvailability()==false){
                return true;
            }
        }
        return false;
    }


    public String compute() throws FileNotFoundException {
        Counter[] counterArray = new Counter[NUMBEROFCOUNTER];
        counterArray[0] = new Counter('A',10);
        counterArray[1] = new Counter('B',15);
        counterArray[2] = new Counter('C',30);
        counterArray[3] = new Counter('D',15);
        sortCounter(counterArray);
        String result = String.format("|%-10s|%-20s|%-15s|%-20s|%-15s|%-8s   |%-8s|\n","Arrival","Start Processing","End Processing","Processing Time","Waiting Time", "Queue", "Counter");
        System.out.println(result);
        results.add(result);
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
                            profit+=c.getTotalPrice();
                            display(c,counterArray[j]);                     //show in the textfile 
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
                            profit+=c.getTotalPrice();
                            display(c,counterArray[j]);     //  show in the textfile  the output 
                            continue;
                        }
                    }
                }
            }
            globalTime++;
        }
        
       // System.out.println("Total completion time: " + (globalTime-1));
        results.add("Total completion time: " + (globalTime-1)+"s"+"?Today's sales : RM "+profit);
        String resultString = "";
        for(String res: results) resultString = resultString + "\n" + res;
        System.out.println("result is " + resultString);
        return resultString;
    }


}
