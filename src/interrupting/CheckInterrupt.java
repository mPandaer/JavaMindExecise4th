package interrupting;

import java.util.concurrent.TimeUnit;

public class CheckInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new CheckTask());
        t1.start();
        TimeUnit.MICROSECONDS.sleep(1);
        t1.interrupt();
        TimeUnit.MICROSECONDS.sleep(1);
        System.exit(0);
    }
}

class CheckTask implements Runnable {
    @Override
    public void run() {
        for (;;) {
            System.out.println(Thread.interrupted());
//            try {
////                TimeUnit.MILLISECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                System.out.println("sleep 被打断");
//            }
        }
    }
}
