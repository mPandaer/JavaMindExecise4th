package interrupting;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyIOInterruptTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new MyIOBlock(System.in));
        System.out.println("开始打断");
        ex.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("开始关闭资源");
        System.in.close();
    }
}
