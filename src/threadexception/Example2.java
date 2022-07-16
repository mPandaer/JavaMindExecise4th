package threadexception;

import concurrent.RunnableTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Example2 {
    static ExecutorService ex = Executors.newCachedThreadPool(new ExceptionThreadFactory());

    public static void main(String[] args) {
        ex.execute(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("我是线程中的异常，你捕获不到");
            }
        });
    }

}

class ExceptionThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println( Thread.currentThread().getName() + "我捕获到了异常"+ t.getName() + " " + e.getMessage());
            }
        });
        return t;
    }
}
