package teamthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadLockedThread {
    public static void main(String[] args) {
        Test t = new Test(new Object(),new Object());
        new Thread(){
            @Override
            public void run() {
                try {
                    t.g();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    t.f();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}

class Test {
    Object lock1, lock2;
    public Test(Object lock1, Object lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    public void f() throws InterruptedException {
        synchronized (lock1) {
                System.out.println("获得了lock1" + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(20);
            synchronized (lock2){
                System.out.println("获得了lock2" + Thread.currentThread().getName());
            }

        }
    }

    public void g() throws InterruptedException {
        synchronized (lock2) {
                System.out.println("获得了lock2" + Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(20);
                synchronized (lock1){
                    System.out.println("获得了lock1" + Thread.currentThread().getName());
                }

        }
    }


}
