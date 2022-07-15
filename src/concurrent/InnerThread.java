package concurrent;

public class InnerThread {
    private final Inner in;
    public InnerThread() {
        in = new Inner();
    }

    class Inner extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "内部线程");
        }
        public Inner() {
            start();
        }
    }

    public static void main(String[] args) {
        new InnerThread();
    }
}
