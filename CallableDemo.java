package Chp00;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //submit accepts both runnable and callable and return an object of Future interface
        Future<Double> future=executorService.submit(new PrintNumber());
        executorService.shutdown();
        //a blocking trunk that may face situations like that future unnecessarily waits for the thread to return the result
/*        try {
            System.out.println("future: "+future.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }*/

        while (!future.isDone()){
            System.out.println("Thread not finished!");
            System.out.println("Main thread does something else!");
        }
        System.out.println(future.get());

    }
}

class PrintNumber implements Callable<Double>{
    @Override
    public Double call() throws Exception {
        Thread.sleep(2000);
        return Math.random();
    }
}