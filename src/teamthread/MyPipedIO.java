package teamthread;


import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyPipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        MyWriter writer = new MyWriter();
        MyReader reader = new MyReader(writer);
        ex.execute(writer);
        ex.execute(reader);
        TimeUnit.MILLISECONDS.sleep(2000);
        ex.shutdownNow();
        System.out.println("任务全部完成");
    }
}

class MyWriter implements Runnable {
    PipedWriter writer = new PipedWriter();

    @Override
    public void run() {

        try {
            while(true) {
                for (char i = 'A'; i<='z'; i++) {
                    writer.write( i);
                    TimeUnit.MILLISECONDS.sleep(20);
                }
            }
        }catch (IOException | InterruptedException ioe) {
            System.out.println(ioe.getMessage() + this.getClass().getName());
        }

    }
    public PipedWriter getWriter() {
        return writer;
    }
}

class MyReader implements Runnable {
    PipedReader reader;
    public MyReader(MyWriter writer) throws IOException {
        reader = new PipedReader(writer.getWriter());
    }

    @Override
    public void run() {

        try{
            while(true){
                System.out.println( (char) reader.read());
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage() + this.getClass().getName());
        }

    }
}
