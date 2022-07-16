package interrupting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterruptStatusTest {
    //    public static void main(String[] args) throws InterruptedException {
//        ExecutorService ex = Executors.newCachedThreadPool();
//        ex.execute(new StatusTask());
//        TimeUnit.MICROSECONDS.sleep(1);
//        ex.shutdownNow();
//        System.exit(0);
//    }
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new StatusTask());
        t.start();
        TimeUnit.MILLISECONDS.sleep(200);
        while(!StatusTask.interrupt()){
            System.out.println(StatusTask.interrupt());
        }


    }
}

class StatusTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("捕获到了打断异常");
        }
        System.out.println("StatusTask 任务结束");

    }

    static boolean interrupt() {
        return Thread.interrupted();
    }
}
