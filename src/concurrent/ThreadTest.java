package concurrent;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ThreadTest extends Thread{
    @Override
    public void run(){
        System.out.println("ThreadTest" + Thread.currentThread().getName()+ "启动");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
        System.out.println("ThreadTest" + Thread.currentThread().getName()+ "结束");
    }

    public ThreadTest() {
        start();
    }

    public static void main(String[] args) {
        new ThreadTest();
    }
}
