package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.*;
import static java.util.concurrent.TimeUnit.*;

interface Task extends Runnable {
};

public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            es.execute(new Task() {
                @Override
                public void run() {
                    System.out.println("执行任务" + currentThread().getName());
                    try {
                        SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("完成任务" + currentThread().getName());
                }
            });

        }
        es.shutdown();
    //        es.execute(new Task() {
    //            @Override
    //            public void run() {
    //                System.out.println("xixi" + currentThread().getName());
    //            }
    //        });
    }
}


