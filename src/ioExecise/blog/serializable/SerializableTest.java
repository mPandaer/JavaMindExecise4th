package ioExecise.blog.serializable;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class SerializableTest {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        File f = new File("dog.out");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(new Dog(12,"john"));
        TimeUnit.SECONDS.sleep(2);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        System.out.println(in.readObject());
    }
}

class Dog implements Serializable {
    int age;
    transient String name;

    public Dog(){
        System.out.println("Dog的默认构造器");
    }
    public Dog(int age,String name) {
        System.out.println("Dog的带参数的构造器");
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("name: %s age: %d\n",name,age);
    }
}
