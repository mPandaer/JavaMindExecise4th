package interrupting;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new LockTask());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("请求打断");
        t1.interrupt();

    }
}

class MyLock {
    private Lock lock = new ReentrantLock();
    public MyLock() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("获得了锁");
        } catch (InterruptedException e) {
            System.out.println("捕获了打断异常");
        }
    }

}


class LockTask implements Runnable{
    MyLock lock = new MyLock();
    @Override
    public void run() {
        System.out.println("等待中");
        lock.f();
        System.out.println("打断了阻塞");
    }
}