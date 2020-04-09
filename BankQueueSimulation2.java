package Chp00;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

/*
this implementation is with additional feature:
simulating breaks of the service after it has started.
Main ideas: 1 one event processed each with yet two kinds of time considered:
   of the current event, end of last event;
   2 limited buffer to prevent burden of reforming data (in the case of unexpected service break)
    -- based on 1.
 */
public class BankQueueSimulation2 {
    static int time = 0;//of finishing last event
    static int currentT = 0;//current time
    static int totalWait = 0;
    static LinkedList<Event> arrivalList = new LinkedList<Event>();
    static LinkedList<Event> departureList = new LinkedList<Event>();
    static int no = 20;//no. of customers
    static int ptr = 0;//pointing to n-th arrival

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {

        System.out.println("Simulation begins.");

        for (int i = 0; i <no ; i++) {//generate arrival events
            Event event = new Event();
            int[] newArrival = generateArrival();
            currentT += newArrival[0];
            event.setTheTime(currentT);
            event.setTransactionLength(newArrival[1]);
            event.setNo(i+1);
            arrivalList.add(event);
        }
        System.out.println(no + " customers.");

        //bootstrap by moving to the first customer
        Event event3;//arrival/departure event holder
        event3 = arrivalList.get(ptr);//point to first arrival
        System.out.print("Customer No." + event3.getNo() + " arrived at " + event3.getTheTime() + ", ");
        System.out.println("transaction: "+event3.getTransactionLength()+" .");
        currentT = event3.getTheTime();
        generateDeparture();
        ptr++;
        //end of boot

        int[] wait = new int[no];//-

        boolean allArrived = false;
        for (int i = 1; i < 2 * no; i++) {//process one event once
            int t1, t2;//for comparison of two nearest events in the two lists
            t1 = arrivalList.get(ptr).getTheTime();
            if(!departureList.isEmpty()){
                t2 = departureList.get(0).getTheTime();
            }else {//in case of zero nearest departure caused by late arrivals
                t2 = t1+1;
            }

            if (t1 < t2 & !allArrived) {//the nearest event is an arrival, not end of arrivals
                event3 = arrivalList.get(ptr);
                System.out.print("Customer No." + event3.getNo() + " arrived at " + event3.getTheTime() + ", ");
                System.out.println("transaction: "+event3.getTransactionLength()+" .");
                generateDeparture();
                ptr++;
                if (ptr == no) {
                    ptr--;
                    allArrived = true;
                }
                currentT = event3.getTheTime();
            } else {
                event3 = departureList.pop();
                totalWait += event3.getWaiting();

                wait[event3.getNo()-1] = event3.getWaiting();//-

                System.out.println("Customer No." + event3.getNo() + " departed at " + event3.getTheTime() + " .");
                currentT = event3.getTheTime();

                if (currentT >= 30 & !departureList.isEmpty()) {
                    //random break from 30th min of service, could happen after a departure
                    if (Math.random() < 0.10) {//the chance
                        int pause = generatePause();
                        System.out.println("\nA "+pause+" min break happened at "+currentT+" min.\n");
                        for (Event e : departureList
                        ) {
                            e.setTheTime(e.getTheTime()+pause);
                            e.setWaiting(e.getWaiting()+pause);

                            wait[event3.getNo()-1] = event3.getWaiting();//-
                        }
                        time += pause;
                    }
                }

            }
        }

        double averageWait = totalWait / no;
        System.out.printf("\nAverage waiting time: %.1f\n", averageWait);

        System.out.println("Waiting array: ");
        for (int i = 0; i <no ; i++) {
            System.out.printf("%3d|",(i+1));
        }
        System.out.println();
        for (int i = 0; i <no ; i++) {
            System.out.printf("%3d ",wait[i]);
        }

    }

    static void generateDeparture() throws CloneNotSupportedException {
        Event event2 = (Event) ((Event) arrivalList.get(ptr)).clone();//get the last
        if (event2.getTheTime() >= time) {//no people waiting
            time = event2.getTheTime() + event2.getTransactionLength();
            event2.setTheTime(time);
            event2.setWaiting(0);
        } else {//some people waiting
            time = time + event2.getTransactionLength();
            event2.setTheTime(time);
            event2.setWaiting(time - event2.getTheTime() + event2.getTransactionLength());
        }
        departureList.add(event2);
    }

    static class Event implements Cloneable {
        int no;//no-th customer
        int theTime;//of arrival or departure
        int transactionLength;
        int waiting;

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

        public int getWaiting() {
            return waiting;
        }

        public void setWaiting(int waiting) {
            this.waiting = waiting;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static int generatePause() {//average 15 min with standard deviation as 2
        int pause = (int) ((new Random().nextGaussian()) * 2 + 15);
        while (pause <= 0) {
            pause = (int) ((new Random().nextGaussian()) * 2 + 15);
        }
        return pause;
    }

    static int[] generateArrival() {
        //customers arrival in average 5 min with 2min sd, stay in avg 10 min with sd 5.
        int arrivalT = (int) ((new Random().nextGaussian()) * 2 + 5);
        while (arrivalT < 0) {//they can arrive at the same time
            arrivalT = (int) ((new Random().nextGaussian()) * 3 + 5);//deliberately increase deviation
        }
        int processT = (int) ((new Random().nextGaussian()) * 5 + 10);
        while (processT <= 0) {
            processT = (int) ((new Random().nextGaussian()) * 8 + 10);
        }

        return new int[]{arrivalT, processT};
    }

}
