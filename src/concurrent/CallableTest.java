package concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));
        return Thread.currentThread().getName() + " CallableTest";
    }
}
class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            list.add(es.submit(new CallableTest()));
        }


        for (Future<String> future : list) {
            System.out.println(future);
            if (future.isDone()) {
                System.out.println(future.get());
            }
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
        }



        System.out.println("退出");
    }
}
