package sharedresource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        LockExample1 le = new LockExample1();
        new Thread(){
            @Override
            public void run() {
                try {
                    le.f1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    le.f1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

class LockExample1 {
    Lock lock = new ReentrantLock();
    public void f1() throws InterruptedException {
        boolean isLocked = lock.tryLock(200,TimeUnit.MILLISECONDS);
        try {
            System.out.println( Thread.currentThread().getName() +"isLocked = " + isLocked);
            System.out.println(Thread.currentThread().getName() + "上锁");
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println("f1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(Thread.currentThread().getName()+"解锁");
            if (isLocked){
                lock.unlock();
            }


        }

    }
}