package teamthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyWaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        MyCar car = new MyCar();
        ex.execute(new MyWaxOn(car));
        ex.execute(new MyWaxOff(car));
        TimeUnit.MILLISECONDS.sleep(100);
        ex.shutdownNow();
    }
}


class MyCar {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void waxOff() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) wait();
    }

    public synchronized void waitForWaxingOff() throws InterruptedException {
        while (waxOn) wait();
    }
}

class MyWaxOn implements Runnable {

    private MyCar car;

    public MyWaxOn(MyCar car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("上蜡");
                car.waxed();
                TimeUnit.MILLISECONDS.sleep(20);
                car.waitForWaxingOff();

            }
        } catch (InterruptedException e) {
            System.out.println("捕获了打断异常 -- " + this.getClass().getName());
        }
        System.out.println(this.getClass().getName() + "任务结束");

    }
}

class MyWaxOff implements Runnable {
    private MyCar car;

    public MyWaxOff(MyCar car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                TimeUnit.MILLISECONDS.sleep(20);
                System.out.println("抛光");
                car.waxOff();
            }
        } catch (InterruptedException e) {
            System.out.println("捕获了打断异常 -- " + this.getClass().getName());
        }
        System.out.println(this.getClass().getName() + "任务结束");
    }
}
