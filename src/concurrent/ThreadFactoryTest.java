package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool(new DaemonThread());

        for (int i = 0; i< 10;i++){
            executor.execute(new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().isDaemon());
                }
            });
        }

    }

}

class DaemonThread implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
