package sharedresource;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalThreadTest {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i< 5; i++) {
            ex.execute(new LocalTask());
        }
    }

}

class LocalTask implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "增加前：" + this);
        LocalVar.increment();
        System.out.println(Thread.currentThread().getName() + "增加后：" + this);
        Thread.yield();


    }

    @Override
    public String toString() {
        return Thread.currentThread().getName() + LocalVar.get();
    }
}

@SuppressWarnings("all")
class LocalVar {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random ran = new Random();
        @Override
        protected Integer initialValue() {
            return ran.nextInt(2000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }
    public static Integer get() {
        return value.get();
    }
}
