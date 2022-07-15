package concurrent;

import java.util.concurrent.TimeUnit;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread2 t2 = new Thread2();
        new Thread1(t2);
        TimeUnit.MILLISECONDS.sleep(100);
        t2.interrupt();
        System.out.println(Thread.currentThread().getName()  + "打断了t2");
    }
}

class Thread1 extends Thread {
    Thread2 t2;
    public Thread1(Thread2 t2) {
        this.t2 = t2;
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + 1);
        }
        try {
            t2.join();
            System.out.println("打断了t2");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i =0;i<20;i++){
            System.out.println(Thread.currentThread().getName() + 2);
        }

    }
}

class Thread2 extends Thread {
    public Thread2() {
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i<20;i++) {
            System.out.println("i = "+i);
            if (!isInterrupted()){
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "我被打断了");
                }
            }

        }
    }
}