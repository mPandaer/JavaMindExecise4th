package interrupting;


import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class MySleepBlock implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("打断异常捕获");
            System.out.println("退出run方法 MySleepBlock结束");
        }

    }
}

class MyIOBlock implements Runnable {
    InputStream in;
    public MyIOBlock(InputStream in) {
        this.in = in;
    }
    @Override
    public void run() {
        try {
            while(true){
                in.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("线程被打断了");
                System.out.println("MyIOBlock 任务结束");
            }
        }
        System.out.println("任务结束了");

    }
}

class MySyncBlock implements Runnable{

    @Override
    public void run() {
        try {
            f();
        } catch (Exception e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("线程被打断了");
            }
        }
        System.out.println("MySyncBlock任务结束");
    }

    public MySyncBlock() {
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    public synchronized void f() {
        while(true) {
            Thread.yield();
        }
    }
}




public class MyInterrupting {
    static ExecutorService ex = Executors.newCachedThreadPool();

    public static void test(Runnable r) throws InterruptedException {
        System.out.println("任务：" + r.getClass().getName());
        Future<?> submit = ex.submit(r);
        TimeUnit.MILLISECONDS.sleep(200);
        submit.cancel(true);
    }

    public static void main(String[] args) throws InterruptedException {
//        test(new MySleepBlock());
//        test(new MyIOBlock(new CharArrayReader(new char[10])));
//        test(new MySyncBlock());
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new MySleepBlock());
        ex.execute(new MyIOBlock(System.in));
        ex.execute(new MySyncBlock());
        TimeUnit.MILLISECONDS.sleep(200);
        ex.shutdownNow();
        TimeUnit.MILLISECONDS.sleep(2000);
        System.exit(0);
    }
}
