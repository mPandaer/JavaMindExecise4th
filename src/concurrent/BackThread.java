package concurrent;

import java.util.concurrent.TimeUnit;

public class BackThread extends Thread{
    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(Thread.currentThread().getName() + " 后台");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("我还活着");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new BackThread();
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(150);
        System.out.println(Thread.currentThread().getName() +" 主线程");
    }
}
