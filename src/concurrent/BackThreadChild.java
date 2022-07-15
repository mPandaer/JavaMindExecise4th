package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BackThreadChild {
    static Thread t1 = new Thread() {
        @Override
        public void run() {
            for (int i = 0; i<10;i++){
                new Thread(){
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + " -- " + this.isDaemon());

                    }
                }.start();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(2);
    }

}
