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
    
    public double getTotalPrice(){
        if(status == 'V') return Main.Vprice * ticket;
        else return Main.Nprice * ticket;
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
