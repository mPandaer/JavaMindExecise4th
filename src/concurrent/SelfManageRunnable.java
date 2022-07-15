package concurrent;



public class SelfManageRunnable implements Runnable{
    private final Thread t = new Thread(this);
    public SelfManageRunnable(boolean isDaemon,int priority) {
        setThread(isDaemon,priority);
        t.start();
    }
    public void setThread(boolean isDaemon, int priority) {
        t.setDaemon(isDaemon);
        t.setPriority(priority);
    }

    @Override
    public void run() {
        System.out.println( Thread.currentThread().getName() +"自管理Runnable");
    }

    public static void main(String[] args) {
        new SelfManageRunnable(false, Thread.NORM_PRIORITY);
    }
}
