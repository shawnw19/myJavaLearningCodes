package Chp00;

import java.util.LinkedList;
import java.util.Random;
/*
improvements on version2: 1 simplified control flow
2 reduction of variables and removal of bootstrap based on 1
 */
public class BankQueueSimulation3 {
    static int time = 0;//of finishing last event
    static int currentT = 0;//current time
    static LinkedList<Event> arrivalList = new LinkedList<Event>();
    static LinkedList<Event> departureList = new LinkedList<Event>();
    static int no = 20;//no. of customers
    static Event event;//temporary event (be it arrival, departure etc) holder

    public static void main(String[] args) {

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
        System.out.println(arrivalList.size() + " customers.");

        int totalWait = 0;//for calculating waiting time
        int[] wait = new int[no];

        for (int i = 0; i < 2*no; i++) {//process one event once
            int t1, t2;//for comparison of two nearest events in the two lists

            if (arrivalList.size()>0) {//not end of arrivals
                t1 = arrivalList.get(0).getTheTime();
            }else {
                t1 = currentT;
            }
            if(!departureList.isEmpty()){
                t2 = departureList.get(0).getTheTime();
            }else {//in case of zero nearest departure caused by late arrivals
                t2 = t1+1;
            }

            if (t1<t2 &arrivalList.size()>0){//the nearest event is an arrival
                event = arrivalList.pop();
                System.out.print("Customer No." + event.getNo() + " arrived at " + event.getTheTime() + ", ");
                System.out.println("transaction: "+event.getTransactionLength()+" .");
                generateDeparture(event);

            }else {
                event = departureList.pop();
                totalWait += event.getWaiting();

                wait[event.getNo()-1] = event.getWaiting();//-

                System.out.println("Customer No." + event.getNo() + " departed at " + event.getTheTime() + " .");
                currentT = event.getTheTime();

                if (currentT >= 30 & !departureList.isEmpty()) {
                    //random break from 30th min of service, could happen after a departure
                    if (Math.random() < 0.10) {//the chance
                        int pause = generatePause();
                        System.out.println("\nA "+pause+" min break happened at "+currentT+" min.\n");
                        for (Event e : departureList
                        ) {
                            e.setTheTime(e.getTheTime()+pause);
                            e.setWaiting(e.getWaiting()+pause);

                            wait[event.getNo()-1] = event.getWaiting();//-
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

    static void generateDeparture(Event arrival) {
        if (arrival.getTheTime() >= time) {//no people waiting
            time = arrival.getTheTime() + arrival.getTransactionLength();
            arrival.setTheTime(time);
            arrival.setWaiting(0);
        } else {//some people waiting
            arrival.setWaiting(time - arrival.getTheTime());
            time = time + arrival.getTransactionLength();
            arrival.setTheTime(time);
        }
        departureList.add(arrival);
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
