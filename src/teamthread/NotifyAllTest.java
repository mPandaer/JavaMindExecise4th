package teamthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyAllTest {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i<5; i++){
            ex.execute(new Task1(t1));
            ex.execute(new Task2(t2));
        }
        ex.execute(new NotifyTask2(t2));


    }
}

class T1 {
    public synchronized void waitT1() throws InterruptedException {
        wait();
        System.out.println(this.getClass().getName());
    }
}

class T2 {
    public synchronized void waitT2() throws InterruptedException {
        wait();
        System.out.println(this.getClass().getName());
    }

    public synchronized void notifyAllT2(){
        notifyAll();
    }
}


class Task1 implements Runnable {
    T1 t;
    public Task1(T1 t) {
        this.t = t;
    }
    @Override
    public void run() {
        try {
            t.waitT1();
            System.out.println("恢复 t1");
        } catch (InterruptedException e) {
            System.out.println("捕获到中断异常" + this.getClass().getName());
        }
    }
}

class Task2 implements Runnable {
    T2 t;
    public Task2(T2 t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            t.waitT2();
            System.out.println("恢复 t2");
        } catch (InterruptedException e) {
            System.out.println("捕获到中断异常" + this.getClass().getName());
        }
    }
}

class NotifyTask2 implements Runnable{
    T2 t2;
    public NotifyTask2(T2 t2) {
        this.t2 =t2;
    }

    @Override
    public void run() {
        t2.notifyAllT2();
    }
}