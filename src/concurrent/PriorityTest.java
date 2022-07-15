package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PriorityTest {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new RunTask(Thread.MIN_PRIORITY));
        }
        executor.execute(new RunTask(Thread.MAX_PRIORITY));
        executor.shutdown();
    }
}

class RunTask implements Runnable {
    private final int priority;

    public RunTask(int priority) {
        this.priority = priority;
    }

    @Override
    public void run() {

        Thread.currentThread().setPriority(priority);
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 50000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}