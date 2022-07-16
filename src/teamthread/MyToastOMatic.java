package teamthread;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class MyToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<MyToast> toaster = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<MyToast> butterers = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<MyToast> finisheds = new LinkedBlockingDeque<>();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new MyToaster(toaster));
        ex.execute(new MyButterer(toaster,butterers));
        ex.execute(new MyJammer(butterers,finisheds));
        ex.execute(new MyEater(finisheds));
        TimeUnit.SECONDS.sleep(1);
        ex.shutdownNow();
        System.exit(0);
    }
}

class MyToast{
    static enum Status {DRY,BUTTER,JAM};
    Status status = Status.DRY;
    int id;
    public MyToast(int id){
        this.id = id;
    }
    public void buttered(){
        status = Status.BUTTER;
    }
    public void jamed() {
        status = Status.JAM;
    }
    public Status getStatus(){
        return status;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return String.format("Toast: %d -- %s",id,status.name());
    }
}

class MyToaster implements Runnable{
    int count = 0;
    LinkedBlockingDeque<MyToast> toastQueue;
    public MyToaster(LinkedBlockingDeque<MyToast> toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(20);
                toastQueue.put(new MyToast(++count));

            }
        }catch (InterruptedException e) {
            System.out.println("捕获的中断异常" + this.getClass().getName());
        }

    }
}

class MyButterer implements Runnable{
    LinkedBlockingDeque<MyToast> toasters,butters;
    public MyButterer(LinkedBlockingDeque<MyToast> toasters,LinkedBlockingDeque<MyToast> butters){
        this.toasters = toasters;
        this.butters = butters;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                MyToast toast = toasters.take();
                TimeUnit.MILLISECONDS.sleep(20);
                toast.buttered();
                System.out.println(toast);
                butters.put(toast);

            }
        }catch (InterruptedException e) {
            System.out.println("捕获的中断异常" + this.getClass().getName());
        }


    }
}

class MyJammer implements Runnable{
    LinkedBlockingDeque<MyToast> butters,finisheds;
    public MyJammer(LinkedBlockingDeque<MyToast> butters,LinkedBlockingDeque<MyToast> finisheds){
        this.butters = butters;
        this.finisheds = finisheds;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                MyToast toast = butters.take();
                TimeUnit.MILLISECONDS.sleep(20);
                toast.jamed();
                System.out.println(toast);
                finisheds.put(toast);

            }
        }catch (InterruptedException e) {
            System.out.println("捕获的中断异常" + this.getClass().getName());
        }


    }



}
class MyEater implements Runnable{
    int count = 0;
    LinkedBlockingDeque<MyToast> finisheds;
    public MyEater(LinkedBlockingDeque<MyToast> finisheds) {
        this.finisheds = finisheds;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                MyToast toast = finisheds.take();
                if (toast.getStatus() != MyToast.Status.JAM || toast.getId() != ++count){
                    System.exit(1);
                }
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("吃了一个吐司：" + toast);
            }
        }catch (InterruptedException e) {
            System.out.println("捕获的中断异常" + this.getClass().getName());
        }

    }
}