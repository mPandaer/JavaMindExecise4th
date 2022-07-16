package threadexception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example3 {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(Thread.currentThread().getName() + " 我捕获到了异常: " + t.getName() +  " ("+e.getMessage() + ")");
            }
        });
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i<10;i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    throw new RuntimeException(Thread.currentThread().getName() + " 我是线程中的异常，你捕获不到，哈哈");
                }
            });
        }


        Thread t1 = new Thread(){
            @Override
            public void run() {
                throw new RuntimeException("我是t1的异常");
            }
        };
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("我捕获到了: " + e.getMessage());
            }
        });
        executor.execute(t1);

    }
}
