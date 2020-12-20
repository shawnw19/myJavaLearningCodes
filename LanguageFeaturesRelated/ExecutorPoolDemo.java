
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new PrintRandomNumber());
        executorService.execute(new PrintRandomNumber());
        executorService.execute(new PrintRandomNumber());
        //another three threads
        executorService.execute(new PrintRandomNumber());
        executorService.execute(new PrintRandomNumber());
        executorService.execute(new PrintRandomNumber());

        executorService.shutdown();
        //shutdown does not means termination
        System.out.println("Is shutdown? "+executorService.isShutdown());

        while (!executorService.isTerminated()){
            System.out.println("Not terminated.");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class PrintRandomNumber implements Runnable{
    @Override
    public void run() {
        String threadName =Thread.currentThread().getName()+ (int)(Math.random()*100);
        System.out.println(threadName +": "+Math.random());
        try {
            Thread.sleep((int)(Math.random()*10000));
            System.out.println(threadName +" finished.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
