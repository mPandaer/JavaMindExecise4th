package threadexception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            executor.execute(new ExceptionTask());
        } catch (Exception e) {
            System.out.println("我抓到你了");
        }
    }
}

class ExceptionTask implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException("嘻嘻，我是线程中的异常，你捕获不到");
    }
}
