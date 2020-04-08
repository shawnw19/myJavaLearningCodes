package Chp00;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

/*
the main ideas of this simulation of the famous problem
is 1. "first come, first serve"
2. two lines processing arrivals and departures respectively
 */
public class BankQueueSimulation {
    static int time = 0;//of finishing last event

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        LinkedList arrivalList = new LinkedList<Event>();
        LinkedList departureList = new LinkedList<Event>();
        Scanner arrivals = new Scanner(new FileReader("ArrivalEvents.txt"));

        System.out.println("Simulation begins.");

        int no=0;//No. of customer
        while (arrivals.hasNext()){//read all arrival events
            Event event = new Event();
            event.setTheTime(arrivals.nextInt());
            event.setTransactionLength(arrivals.nextInt());
            no++;
            event.setNo(no);
            arrivalList.add(event);

        }

        System.out.println(no+" customers.");

        for (int i = 0; i <no ; i++) {//generate departure events
            Event event2= (Event) ((Event)arrivalList.get(i)).clone();//get() passes a reference

            if (event2.getTheTime()>=time){//no people waiting
               time = event2.getTheTime()+event2.getTransactionLength();
               event2.setTheTime(time);
            }else {//some people waiting
                time = time +event2.getTransactionLength();
                event2.setTheTime(time);
            }
            departureList.add(event2);
        }

        Event event3;//arrival/departure event holder
        while (!arrivalList.isEmpty()){//the arrivalList will be popped out first
            //int temp1 = 0, temp2=0;//both start at first event
            int t1, t2;
            t1= ((Event)arrivalList.get(0)).getTheTime();
            t2= ((Event)departureList.get(0)).getTheTime();

            //Timer timer = new Timer();
            if (t1<=t2){//the nearest event is an arrival
                event3= (Event) arrivalList.pop();
                System.out.println("Customer No."+event3.getNo() +" arrived at "+event3.getTheTime()+" .");
            }else {
                event3 = (Event) departureList.pop();
                System.out.println("Customer No."+event3.getNo() +" departed at "+event3.getTheTime()+" .");
            }
        }

        while (!departureList.isEmpty()){//followed by remaining departures
            event3 = (Event) departureList.pop();
            System.out.println("Customer No."+event3.getNo() +" departed at "+event3.getTheTime()+" .");
        }

    }

    static class Event implements Cloneable{
        int no;//no-th customer
        int theTime;//of arrival or departure
        int transactionLength;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getTheTime() {
            return theTime;
        }

        public void setTheTime(int theTime) {
            this.theTime = theTime;
        }

        public int getTransactionLength() {
            return transactionLength;
        }

        public void setTransactionLength(int transactionLength) {
            this.transactionLength = transactionLength;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}

/*
I tried Timer and TimerTask to simulate the waiting process
by causing each output wait a bit before execution, but it didn't
work well because the order was not followed.
 */
