package interrupting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FlagInterruptingTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i  =0; i< 5; i++) {
            ex.execute(new FlagTask());
        }
        ex.shutdown();
        TimeUnit.MILLISECONDS.sleep(10);
        FlagTask.cancel();
        if (!ex.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("存在某些任务没有完成");
        }
        System.out.println("进程退出");
    }
}

class FlagTask implements Runnable {
    private static final Object lock = new Object();
    private static boolean isCanceled = false;
    private static volatile int sum = 0;
    @Override
    public void run() {

        while(!isCanceled){
            addOne();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addOne() {

        synchronized (lock){
            sum += 1;
            System.out.println("Sum: " + sum);
        }

    }
    public static void cancel() {
        isCanceled = true;
    }
}
