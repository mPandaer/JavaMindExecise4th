package concurrent;

import java.util.concurrent.TimeUnit;

public class RunnableTest implements Runnable{
    @Override
    public void run() {
        System.out.println( Thread.currentThread().getName() + "开始执行任务");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "执行任务完成");
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new RunnableTest()).start();
        System.out.println(Thread.currentThread().getName() + "开始执行任务");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + "执行任务完成");
    }
}
