package sharedresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncRock {
    public static void main(String[] args) {
        RockExample re = new RockExample();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i<3; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    re.f1();
                }
            });
        }
    }
}

class RockExample {
    public void f1() {
        for (int i = 1; i<5; i++) {
            System.out.print(Thread.currentThread().getName() + " "+i + " ");
        }
        synchronized (this) {
            System.out.println();
            System.out.println("---------------------");
            System.out.println(Thread.currentThread().getName() + "锁对象呢");
            System.out.println("---------------------");
        }
        for (int i = 1; i<5; i++) {
            System.out.print(Thread.currentThread().getName() + (-i) + " ");
        }
    }
}
