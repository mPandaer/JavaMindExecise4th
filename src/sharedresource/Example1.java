package sharedresource;

public class Example1 {
    public static void main(String[] args) {
        Shared s = new Shared();
        new Thread() {
            @Override
            public void run() {
                s.f1();
            }
        }.start();
    }
}

class Shared {
    synchronized public void f1() {
        System.out.println("f1()");
        f2();
    }
    synchronized public void f2() {
        System.out.println("f2()");
        f3();
    }
    synchronized public void f3() {
        System.out.println("f3()");
    }
}


