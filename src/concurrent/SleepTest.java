package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 验证sleep是否会释放锁
 */
public class SleepTest {
    synchronized void f() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "获得锁");
        int sum = 0;
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i<5000;i++){
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + "释放锁");
    }

    public static void main(String[] args) {
        SleepTest st = new SleepTest();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    st.f();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    st.f();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        t1.start();
        t2.start();
    }
}


